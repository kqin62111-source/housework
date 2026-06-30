clear; close all; clc;

rootDir = fileparts(mfilename('fullpath'));
outDir = fullfile(rootDir, 'matlab_figures');
if ~exist(outDir, 'dir')
    mkdir(outDir);
end

days = (1:30)';
pm25 = [75;80;82;78;85;90;88;92;94;96;95;97;98;96;95; ...
        92;90;88;85;83;80;78;75;73;70;68;66;64;62;60];

trainX = days(1:25);
trainY = pm25(1:25);
testX = days(26:30);
testY = pm25(26:30);

coef2 = polyfit_lu(trainX, trainY, 2);
coef3 = polyfit_lu(trainX, trainY, 3);
pred2 = polyval(coef2, testX);
pred3 = polyval(coef3, testX);

alpha = 0.6;
[predExp, smoothLevel] = exp_smooth_forecast(trainY, alpha, numel(testX));

mae2 = mean(abs(testY - pred2));
rmse2 = sqrt(mean((testY - pred2).^2));
mae3 = mean(abs(testY - pred3));
rmse3 = sqrt(mean((testY - pred3).^2));
maeExp = mean(abs(testY - predExp));
rmseExp = sqrt(mean((testY - predExp).^2));

exposureActual = composite_simpson(testX, testY);
exposureQuad = composite_simpson(testX, pred2);
exposureCubic = composite_simpson(testX, pred3);
exposureExp = composite_simpson(testX, predExp);

slopeActual = center_diff(testX, testY);
slopeQuad = center_diff(testX, pred2);
slopeCubic = center_diff(testX, pred3);
slopeExp = center_diff(testX, predExp);

fig1 = figure('Visible', 'off', 'Color', 'w', 'Position', [100 100 1500 680]);
tiledlayout(1, 2, 'Padding', 'compact', 'TileSpacing', 'compact');

nexttile;
plot(testX, testY, 'o-', 'LineWidth', 2.2, 'MarkerSize', 7); hold on;
plot(testX, pred2, 's--', 'LineWidth', 2.0, 'MarkerSize', 6);
plot(testX, pred3, 'd--', 'LineWidth', 2.0, 'MarkerSize', 6);
plot(testX, predExp, '^-', 'LineWidth', 2.0, 'MarkerSize', 6);
grid on; box on;
xlabel('Day');
ylabel('PM2.5 concentration');
title('Validation prediction curves');
legend({'Observed', 'Quadratic LS (LU)', 'Cubic LS (LU)', 'Exp. smoothing'}, ...
       'Location', 'southwest');

nexttile;
barVals = [exposureActual, exposureQuad, exposureCubic, exposureExp];
bar(1:numel(barVals), barVals, 0.62);
grid on; box on;
ylabel('Simpson exposure');
title('Cumulative exposure on days 26-30');
xticks(1:numel(barVals));
xticklabels({'Observed','Quadratic','Cubic','ExpSmooth'});
for i = 1:numel(barVals)
    text(i, barVals(i) + 4, sprintf('%.1f', barVals(i)), ...
        'HorizontalAlignment', 'center', 'FontSize', 10);
end

exportgraphics(fig1, fullfile(outDir, 'fig_4_5_extension_prediction.png'), 'Resolution', 300);
close(fig1);

meanOriginal = mean(pm25);
target = 35;
cost = @(k) 50 .* k.^2;
meanAfter = @(k) meanOriginal .* (1 - 0.12 .* k);
f = @(k) meanAfter(k) - target;

kGrid = linspace(0, 6, 401);
meanGrid = meanAfter(kGrid);
costGrid = cost(kGrid);
[kSecant, secHist] = secant_root(f, 3, 5.5, 1e-10, 12);

targets = [45, 40, 35, 33];
kTargets = (1 - targets ./ meanOriginal) ./ 0.12;
costTargets = cost(kTargets);

fineX = linspace(1, 30, 117)';
fineY = spline(days, pm25, fineX);
exposureBefore = composite_simpson(fineX, fineY);
exposureAfter = composite_simpson(fineX, fineY .* (1 - 0.12 .* kSecant));

h = 1e-3;
meanSensitivity = (meanAfter(kSecant + h) - meanAfter(kSecant - h)) / (2 * h);
costSensitivity = (cost(kSecant + h) - cost(kSecant - h)) / (2 * h);

fig2 = figure('Visible', 'off', 'Color', 'w', 'Position', [100 100 1500 680]);
tiledlayout(1, 2, 'Padding', 'compact', 'TileSpacing', 'compact');

nexttile;
yyaxis left;
plot(kGrid, meanGrid, 'LineWidth', 2.3); hold on;
yline(target, '--', 'Target 35', 'LineWidth', 1.5);
plot(kSecant, meanAfter(kSecant), 'o', 'MarkerSize', 8, 'LineWidth', 2);
ylabel('Monthly mean PM2.5');
yyaxis right;
plot(kGrid, costGrid, 'LineWidth', 2.3);
ylabel('Cost');
grid on; box on;
xlabel('Control intensity k');
title('Compliance-cost tradeoff');
legend({'Controlled mean','Target','Secant root','Cost'}, 'Location', 'northeast');

nexttile;
iters = 0:(numel(secHist)-1);
plot(iters, secHist, 'o-', 'LineWidth', 2.3, 'MarkerSize', 7); hold on;
yline(kSecant, '--', sprintf('k*=%.4f', kSecant), 'LineWidth', 1.5);
grid on; box on;
xlabel('Secant iteration');
ylabel('k');
title('Secant method convergence');

exportgraphics(fig2, fullfile(outDir, 'fig_4_6_extension_control.png'), 'Resolution', 300);
close(fig2);

resultFile = fullfile(outDir, 'pm25_extension_results.txt');
fid = fopen(resultFile, 'w');
fprintf(fid, 'alpha=%.4f\n', alpha);
fprintf(fid, 'smooth_level=%.6f\n', smoothLevel);
fprintf(fid, 'quadratic_mae=%.6f\nquadratic_rmse=%.6f\n', mae2, rmse2);
fprintf(fid, 'cubic_mae=%.6f\ncubic_rmse=%.6f\n', mae3, rmse3);
fprintf(fid, 'exp_mae=%.6f\nexp_rmse=%.6f\n', maeExp, rmseExp);
fprintf(fid, 'simpson_actual=%.6f\nsimpson_quadratic=%.6f\nsimpson_cubic=%.6f\nsimpson_exp=%.6f\n', ...
        exposureActual, exposureQuad, exposureCubic, exposureExp);
fprintf(fid, 'center_slope_actual=%.6f\ncenter_slope_quadratic=%.6f\ncenter_slope_cubic=%.6f\ncenter_slope_exp=%.6f\n', ...
        mean(slopeActual), mean(slopeQuad), mean(slopeCubic), mean(slopeExp));
fprintf(fid, 'k_secant=%.10f\n', kSecant);
fprintf(fid, 'cost_at_target=%.6f\n', cost(kSecant));
fprintf(fid, 'exposure_before=%.6f\nexposure_after=%.6f\n', exposureBefore, exposureAfter);
fprintf(fid, 'mean_sensitivity=%.6f\ncost_sensitivity=%.6f\n', meanSensitivity, costSensitivity);
for i = 1:numel(targets)
    fprintf(fid, 'target_%g_k=%.6f,target_%g_cost=%.6f\n', ...
        targets(i), kTargets(i), targets(i), costTargets(i));
end
fclose(fid);

disp(['Saved MATLAB figures to: ', outDir]);
disp(['Saved result summary to: ', resultFile]);

function coef = polyfit_lu(x, y, degree)
    x = x(:);
    y = y(:);
    A = zeros(numel(x), degree + 1);
    for j = 0:degree
        A(:, j + 1) = x .^ (degree - j);
    end
    normalMat = A' * A;
    rhs = A' * y;
    [L, U, P] = lu(normalMat);
    coef = U \ (L \ (P * rhs));
    coef = coef(:).';
end

function [forecast, level] = exp_smooth_forecast(y, alpha, horizon)
    y = y(:);
    level = y(1);
    for i = 2:numel(y)
        level = alpha * y(i) + (1 - alpha) * level;
    end
    forecast = repmat(level, horizon, 1);
end

function val = composite_simpson(x, y)
    x = x(:);
    y = y(:);
    n = numel(x);
    if mod(n, 2) == 0
        error('Composite Simpson rule requires an odd number of points.');
    end
    h = (x(end) - x(1)) / (n - 1);
    val = h / 3 * (y(1) + y(end) + 4 * sum(y(2:2:end-1)) + 2 * sum(y(3:2:end-2)));
end

function d = center_diff(x, y)
    x = x(:);
    y = y(:);
    d = (y(3:end) - y(1:end-2)) ./ (x(3:end) - x(1:end-2));
end

function [root, hist] = secant_root(fun, x0, x1, tol, maxIter)
    hist = [x0; x1];
    f0 = fun(x0);
    f1 = fun(x1);
    for i = 1:maxIter
        x2 = x1 - f1 * (x1 - x0) / (f1 - f0);
        hist(end + 1, 1) = x2; %#ok<AGROW>
        if abs(x2 - x1) < tol || abs(fun(x2)) < tol
            root = x2;
            return;
        end
        x0 = x1;
        f0 = f1;
        x1 = x2;
        f1 = fun(x1);
    end
    root = hist(end);
end
