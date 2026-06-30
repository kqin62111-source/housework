package cn.wolfcode.controller;

import cn.wolfcode.domain.*;
import cn.wolfcode.service.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/report")
public class ReportController {

    @Autowired
    private IStudentService studentService;

    @Autowired
    private IScoreService scoreService;

    @Autowired
    private IAttendanceService attendanceService;

    @Autowired
    private IGradeService gradeService;

    @Autowired
    private IClazzService clazzService;

    @Autowired
    private IDepartmentService departmentService;

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IScheduleService scheduleService;

    @RequestMapping("/studentReport")
    public String studentReport(Model model, StudentReportQuery query) {
        QueryObjet allQo = new QueryObjet();
        allQo.setPageSize(1000);
        List<Student> allStudents = studentService.selectAllStudent(allQo).getList();
        
        List<Student> filteredStudents = allStudents;
        if (query != null) {
            if (query.getGrade() != null && !query.getGrade().isEmpty()) {
                filteredStudents = filteredStudents.stream()
                        .filter(s -> query.getGrade().equals(s.getGrade()))
                        .collect(Collectors.toList());
            }
            if (query.getClazzName() != null && !query.getClazzName().isEmpty()) {
                filteredStudents = filteredStudents.stream()
                        .filter(s -> s.getClassName() != null && s.getClassName().contains(query.getClazzName()))
                        .collect(Collectors.toList());
            }
        }

        List<Grade> grades = gradeService.selectAllGrade(new QueryObjet()).getList();
        List<Clazz> clazzes = clazzService.selectAllClazz(new QueryObjet()).getList();

        Map<String, Integer> gradeCount = new HashMap<>();
        Map<String, Integer> genderCount = new HashMap<>();
        Map<String, Integer> classCount = new HashMap<>();
        Map<String, Map<String, Integer>> gradeGenderCount = new HashMap<>();

        int maleCount = 0;
        int femaleCount = 0;
        int totalAge = 0;
        int minAge = Integer.MAX_VALUE;
        int maxAge = Integer.MIN_VALUE;

        for (Student s : filteredStudents) {
            gradeCount.merge(s.getGrade(), 1, Integer::sum);
            genderCount.merge(s.getGender(), 1, Integer::sum);
            classCount.merge(s.getClassName(), 1, Integer::sum);

            gradeGenderCount.computeIfAbsent(s.getGrade(), k -> new HashMap<>())
                    .merge(s.getGender(), 1, Integer::sum);

            if ("男".equals(s.getGender())) {
                maleCount++;
            } else if ("女".equals(s.getGender())) {
                femaleCount++;
            }

            if (s.getAge() != null) {
                totalAge += s.getAge();
                minAge = Math.min(minAge, s.getAge());
                maxAge = Math.max(maxAge, s.getAge());
            }
        }

        double malePercentage = filteredStudents.size() > 0 ? Math.round((maleCount * 100.0 / filteredStudents.size()) * 100) / 100.0 : 0;
        double femalePercentage = filteredStudents.size() > 0 ? Math.round((femaleCount * 100.0 / filteredStudents.size()) * 100) / 100.0 : 0;
        double avgAge = filteredStudents.size() > 0 ? Math.round((totalAge * 1.0 / filteredStudents.size()) * 100) / 100.0 : 0;

        List<Map<String, Object>> gradeDetailList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : gradeCount.entrySet()) {
            Map<String, Object> detail = new HashMap<>();
            detail.put("grade", entry.getKey());
            detail.put("count", entry.getValue());
            detail.put("male", gradeGenderCount.getOrDefault(entry.getKey(), new HashMap<>()).getOrDefault("男", 0));
            detail.put("female", gradeGenderCount.getOrDefault(entry.getKey(), new HashMap<>()).getOrDefault("女", 0));
            detail.put("percentage", filteredStudents.size() > 0 ? 
                    Math.round((entry.getValue() * 100.0 / filteredStudents.size()) * 100) / 100.0 : 0);
            gradeDetailList.add(detail);
        }

        gradeDetailList.sort((a, b) -> ((Comparable) b.get("count")).compareTo(a.get("count")));

        model.addAttribute("totalStudents", filteredStudents.size());
        model.addAttribute("gradeCount", gradeCount);
        model.addAttribute("genderCount", genderCount);
        model.addAttribute("classCount", classCount);
        model.addAttribute("maleCount", maleCount);
        model.addAttribute("femaleCount", femaleCount);
        model.addAttribute("malePercentage", malePercentage);
        model.addAttribute("femalePercentage", femalePercentage);
        model.addAttribute("avgAge", avgAge);
        model.addAttribute("minAge", minAge == Integer.MAX_VALUE ? 0 : minAge);
        model.addAttribute("maxAge", maxAge == Integer.MIN_VALUE ? 0 : maxAge);
        model.addAttribute("gradeDetailList", gradeDetailList);
        model.addAttribute("grades", grades);
        model.addAttribute("clazzes", clazzes);
        model.addAttribute("query", query);

        return "report/studentReport";
    }

    @RequestMapping("/scoreReport")
    public String scoreReport(Model model, ScoreReportQuery query) {
        QueryObjet allQo = new QueryObjet();
        allQo.setPageSize(1000);
        List<Score> allScores = scoreService.selectAllScore(allQo).getList();
        
        List<Score> filteredScores = allScores;
        if (query != null) {
            if (query.getCourseName() != null && !query.getCourseName().isEmpty()) {
                filteredScores = filteredScores.stream()
                        .filter(s -> s.getCourseName() != null && s.getCourseName().contains(query.getCourseName()))
                        .collect(Collectors.toList());
            }
            if (query.getExamType() != null && !query.getExamType().isEmpty()) {
                filteredScores = filteredScores.stream()
                        .filter(s -> query.getExamType().equals(s.getExamType()))
                        .collect(Collectors.toList());
            }
        }

        List<Course> courses = courseService.selectAllCourse(new QueryObjet()).getList();

        Map<String, Double> courseAvgScore = new HashMap<>();
        Map<String, Integer> courseCount = new HashMap<>();
        Map<String, Double> courseMaxScore = new HashMap<>();
        Map<String, Double> courseMinScore = new HashMap<>();
        Map<String, Integer> coursePassCount = new HashMap<>();
        Map<String, Integer> courseFailCount = new HashMap<>();
        Map<String, Double> courseExcellentCount = new HashMap<>();
        Map<String, Double> courseGoodCount = new HashMap<>();

        double totalScore = 0;
        int highScoreCount = 0;
        int lowScoreCount = 0;

        for (Score s : filteredScores) {
            String courseName = s.getCourseName();
            double score = s.getScore().doubleValue();

            courseCount.merge(courseName, 1, Integer::sum);
            courseAvgScore.merge(courseName, score, Double::sum);

            courseMaxScore.merge(courseName, score, Double::max);
            courseMinScore.merge(courseName, score, Double::min);

            if (score >= 60) {
                coursePassCount.merge(courseName, 1, Integer::sum);
            } else {
                courseFailCount.merge(courseName, 1, Integer::sum);
            }

            if (score >= 90) {
                courseExcellentCount.merge(courseName, 1.0, Double::sum);
                highScoreCount++;
            } else if (score >= 80) {
                courseGoodCount.merge(courseName, 1.0, Double::sum);
            }

            if (score < 60) {
                lowScoreCount++;
            }

            totalScore += score;
        }

        Map<String, Double> courseAvgResult = new HashMap<>();
        Map<String, Double> coursePassRate = new HashMap<>();
        Map<String, Double> courseExcellentRate = new HashMap<>();

        for (Map.Entry<String, Double> entry : courseAvgScore.entrySet()) {
            String courseName = entry.getKey();
            double avg = entry.getValue() / courseCount.get(courseName);
            courseAvgResult.put(courseName, Math.round(avg * 100.0) / 100.0);

            int total = courseCount.get(courseName);
            int pass = coursePassCount.getOrDefault(courseName, 0);
            double rate = total > 0 ? Math.round((pass * 100.0 / total) * 100) / 100.0 : 0;
            coursePassRate.put(courseName, rate);

            double excellentDouble = courseExcellentCount.getOrDefault(courseName, 0.0);
            int excellent = (int) excellentDouble;
            double excellentRate = total > 0 ? Math.round((excellent * 100.0 / total) * 100) / 100.0 : 0;
            courseExcellentRate.put(courseName, excellentRate);
        }

        double overallAvg = filteredScores.size() > 0 ? Math.round((totalScore / filteredScores.size()) * 100) / 100.0 : 0;
        double highScoreRate = filteredScores.size() > 0 ? Math.round((highScoreCount * 100.0 / filteredScores.size()) * 100) / 100.0 : 0;
        double lowScoreRate = filteredScores.size() > 0 ? Math.round((lowScoreCount * 100.0 / filteredScores.size()) * 100) / 100.0 : 0;

        List<Map<String, Object>> courseDetailList = new ArrayList<>();
        for (Map.Entry<String, Double> entry : courseAvgResult.entrySet()) {
            Map<String, Object> detail = new HashMap<>();
            detail.put("courseName", entry.getKey());
            detail.put("avgScore", entry.getValue());
            detail.put("maxScore", courseMaxScore.get(entry.getKey()));
            detail.put("minScore", courseMinScore.get(entry.getKey()));
            detail.put("count", courseCount.get(entry.getKey()));
            detail.put("passCount", coursePassCount.getOrDefault(entry.getKey(), 0));
            detail.put("failCount", courseFailCount.getOrDefault(entry.getKey(), 0));
            detail.put("passRate", coursePassRate.get(entry.getKey()));
            detail.put("excellentRate", courseExcellentRate.get(entry.getKey()));
            courseDetailList.add(detail);
        }

        courseDetailList.sort((a, b) -> ((Comparable) b.get("avgScore")).compareTo(a.get("avgScore")));

        model.addAttribute("totalScores", filteredScores.size());
        model.addAttribute("overallAvg", overallAvg);
        model.addAttribute("highScoreRate", highScoreRate);
        model.addAttribute("lowScoreRate", lowScoreRate);
        model.addAttribute("courseAvgResult", courseAvgResult);
        model.addAttribute("courseMaxScore", courseMaxScore);
        model.addAttribute("courseMinScore", courseMinScore);
        model.addAttribute("coursePassRate", coursePassRate);
        model.addAttribute("coursePassCount", coursePassCount);
        model.addAttribute("courseFailCount", courseFailCount);
        model.addAttribute("courseDetailList", courseDetailList);
        model.addAttribute("courses", courses);
        model.addAttribute("query", query);

        return "report/scoreReport";
    }

    @RequestMapping("/attendanceReport")
    public String attendanceReport(Model model, AttendanceReportQuery query) {
        QueryObjet allQo = new QueryObjet();
        allQo.setPageSize(1000);
        List<Attendance> allAttendances = attendanceService.selectAllAttendance(allQo).getList();

        List<Attendance> filteredAttendances = allAttendances;
        if (query != null) {
            if (query.getStudentName() != null && !query.getStudentName().isEmpty()) {
                filteredAttendances = filteredAttendances.stream()
                        .filter(a -> a.getStudentName() != null && a.getStudentName().contains(query.getStudentName()))
                        .collect(Collectors.toList());
            }
            if (query.getStartDate() != null && !query.getStartDate().isEmpty()) {
                filteredAttendances = filteredAttendances.stream()
                        .filter(a -> a.getAttendanceDate() != null && a.getAttendanceDate().compareTo(query.getStartDate()) >= 0)
                        .collect(Collectors.toList());
            }
            if (query.getEndDate() != null && !query.getEndDate().isEmpty()) {
                filteredAttendances = filteredAttendances.stream()
                        .filter(a -> a.getAttendanceDate() != null && a.getAttendanceDate().compareTo(query.getEndDate()) <= 0)
                        .collect(Collectors.toList());
            }
        }

        Map<String, Integer> statusCount = new HashMap<>();
        Map<String, Integer> dateCount = new HashMap<>();
        Map<String, Map<String, Integer>> studentStatusCount = new HashMap<>();

        int presentCount = 0;
        int absentCount = 0;
        int lateCount = 0;
        int leaveCount = 0;
        int earlyLeaveCount = 0;

        Set<String> studentSet = new HashSet<>();

        for (Attendance a : filteredAttendances) {
            statusCount.merge(a.getStatus(), 1, Integer::sum);
            dateCount.merge(a.getAttendanceDate(), 1, Integer::sum);
            studentSet.add(a.getStudentName());

            studentStatusCount.computeIfAbsent(a.getStudentName(), k -> new HashMap<>())
                    .merge(a.getStatus(), 1, Integer::sum);

            switch (a.getStatus()) {
                case "出勤":
                    presentCount++;
                    break;
                case "缺勤":
                    absentCount++;
                    break;
                case "迟到":
                    lateCount++;
                    break;
                case "请假":
                    leaveCount++;
                    break;
                case "早退":
                    earlyLeaveCount++;
                    break;
            }
        }

        double attendanceRate = filteredAttendances.size() > 0 ? Math.round((presentCount * 100.0 / filteredAttendances.size()) * 100) / 100.0 : 0;
        double absentRate = filteredAttendances.size() > 0 ? Math.round((absentCount * 100.0 / filteredAttendances.size()) * 100) / 100.0 : 0;
        double lateRate = filteredAttendances.size() > 0 ? Math.round((lateCount * 100.0 / filteredAttendances.size()) * 100) / 100.0 : 0;
        double leaveRate = filteredAttendances.size() > 0 ? Math.round((leaveCount * 100.0 / filteredAttendances.size()) * 100) / 100.0 : 0;

        List<Map<String, Object>> studentAttendanceList = new ArrayList<>();
        for (String student : studentSet) {
            Map<String, Integer> statusMap = studentStatusCount.getOrDefault(student, new HashMap<>());
            int total = statusMap.values().stream().mapToInt(Integer::intValue).sum();
            int pres = statusMap.getOrDefault("出勤", 0);
            double rate = total > 0 ? Math.round((pres * 100.0 / total) * 100) / 100.0 : 0;

            Map<String, Object> detail = new HashMap<>();
            detail.put("studentName", student);
            detail.put("total", total);
            detail.put("present", pres);
            detail.put("absent", statusMap.getOrDefault("缺勤", 0));
            detail.put("late", statusMap.getOrDefault("迟到", 0));
            detail.put("leave", statusMap.getOrDefault("请假", 0));
            detail.put("rate", rate);
            studentAttendanceList.add(detail);
        }

        studentAttendanceList.sort((a, b) -> ((Comparable) a.get("rate")).compareTo(b.get("rate")));

        List<Map<String, Object>> dateDetailList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : dateCount.entrySet()) {
            dateDetailList.add(new HashMap<String, Object>() {{
                put("date", entry.getKey());
                put("count", entry.getValue());
            }});
        }
        dateDetailList.sort((a, b) -> ((Comparable) a.get("date")).compareTo(b.get("date")));

        model.addAttribute("totalAttendances", filteredAttendances.size());
        model.addAttribute("totalStudents", studentSet.size());
        model.addAttribute("statusCount", statusCount);
        model.addAttribute("dateCount", dateCount);
        model.addAttribute("presentCount", presentCount);
        model.addAttribute("absentCount", absentCount);
        model.addAttribute("lateCount", lateCount);
        model.addAttribute("leaveCount", leaveCount);
        model.addAttribute("earlyLeaveCount", earlyLeaveCount);
        model.addAttribute("attendanceRate", attendanceRate);
        model.addAttribute("absentRate", absentRate);
        model.addAttribute("lateRate", lateRate);
        model.addAttribute("leaveRate", leaveRate);
        model.addAttribute("studentAttendanceList", studentAttendanceList);
        model.addAttribute("dateDetailList", dateDetailList);
        model.addAttribute("query", query);

        return "report/attendanceReport";
    }

    @RequestMapping("/employeeReport")
    public String employeeReport(Model model, EmployeeReportQuery query) {
        QueryObjet allQo = new QueryObjet();
        allQo.setPageSize(1000);
        List<Employee> allEmployees = employeeService.selectAllEmployee(allQo).getList();

        List<Employee> filteredEmployees = allEmployees;
        if (query != null) {
            if (query.getDepartmentName() != null && !query.getDepartmentName().isEmpty()) {
                filteredEmployees = filteredEmployees.stream()
                        .filter(e -> e.getDepartmentName() != null && e.getDepartmentName().contains(query.getDepartmentName()))
                        .collect(Collectors.toList());
            }
            if (query.getPosition() != null && !query.getPosition().isEmpty()) {
                filteredEmployees = filteredEmployees.stream()
                        .filter(e -> e.getPosition() != null && e.getPosition().contains(query.getPosition()))
                        .collect(Collectors.toList());
            }
        }

        List<Department> departments = departmentService.selectAllDepartment(new QueryObjet()).getList();

        Map<String, Integer> departmentCount = new HashMap<>();
        Map<String, Integer> positionCount = new HashMap<>();

        for (Employee e : filteredEmployees) {
            departmentCount.merge(e.getDepartmentName(), 1, Integer::sum);
            positionCount.merge(e.getPosition(), 1, Integer::sum);
        }

        List<Map<String, Object>> departmentDetailList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : departmentCount.entrySet()) {
            Map<String, Object> detail = new HashMap<>();
            detail.put("departmentName", entry.getKey());
            detail.put("count", entry.getValue());
            detail.put("percentage", filteredEmployees.size() > 0 ?
                    Math.round((entry.getValue() * 100.0 / filteredEmployees.size()) * 100) / 100.0 : 0);
            departmentDetailList.add(detail);
        }

        departmentDetailList.sort((a, b) -> ((Comparable) b.get("count")).compareTo(a.get("count")));

        model.addAttribute("totalEmployees", filteredEmployees.size());
        model.addAttribute("departmentCount", departmentCount);
        model.addAttribute("positionCount", positionCount);
        model.addAttribute("departmentDetailList", departmentDetailList);
        model.addAttribute("departments", departments);
        model.addAttribute("query", query);

        return "report/employeeReport";
    }

    @RequestMapping("/scheduleReport")
    public String scheduleReport(Model model, ScheduleReportQuery query) {
        QueryObjet allQo = new QueryObjet();
        allQo.setPageSize(1000);
        List<Schedule> allSchedules = scheduleService.selectAllSchedule(allQo).getList();

        List<Schedule> filteredSchedules = allSchedules;
        if (query != null) {
            if (query.getClassName() != null && !query.getClassName().isEmpty()) {
                filteredSchedules = filteredSchedules.stream()
                        .filter(s -> s.getClassName() != null && s.getClassName().contains(query.getClassName()))
                        .collect(Collectors.toList());
            }
            if (query.getTeacherName() != null && !query.getTeacherName().isEmpty()) {
                filteredSchedules = filteredSchedules.stream()
                        .filter(s -> s.getTeacherName() != null && s.getTeacherName().contains(query.getTeacherName()))
                        .collect(Collectors.toList());
            }
            if (query.getDayOfWeek() != null && !query.getDayOfWeek().isEmpty()) {
                filteredSchedules = filteredSchedules.stream()
                        .filter(s -> query.getDayOfWeek().equals(s.getDayOfWeek()))
                        .collect(Collectors.toList());
            }
        }

        List<Course> courses = courseService.selectAllCourse(new QueryObjet()).getList();
        List<Clazz> clazzes = clazzService.selectAllClazz(new QueryObjet()).getList();

        Map<String, Integer> courseCount = new HashMap<>();
        Map<String, Integer> classCount = new HashMap<>();
        Map<String, Integer> teacherCount = new HashMap<>();
        Map<String, Integer> dayCount = new HashMap<>();
        Map<String, Integer> classroomCount = new HashMap<>();
        Map<String, Integer> periodCount = new HashMap<>();

        Set<String> uniqueTeachers = new HashSet<>();
        Set<String> uniqueClasses = new HashSet<>();
        Set<String> uniqueCourses = new HashSet<>();

        for (Schedule s : filteredSchedules) {
            courseCount.merge(s.getCourseName(), 1, Integer::sum);
            classCount.merge(s.getClassName(), 1, Integer::sum);
            teacherCount.merge(s.getTeacherName(), 1, Integer::sum);
            dayCount.merge(s.getDayOfWeek(), 1, Integer::sum);
            classroomCount.merge(s.getClassroom(), 1, Integer::sum);
            periodCount.merge(String.valueOf(s.getPeriod()), 1, Integer::sum);

            uniqueTeachers.add(s.getTeacherName());
            uniqueClasses.add(s.getClassName());
            uniqueCourses.add(s.getCourseName());
        }

        List<Map<String, Object>> teacherDetailList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : teacherCount.entrySet()) {
            Map<String, Object> detail = new HashMap<>();
            detail.put("teacherName", entry.getKey());
            detail.put("periods", entry.getValue());
            detail.put("percentage", filteredSchedules.size() > 0 ?
                    Math.round((entry.getValue() * 100.0 / filteredSchedules.size()) * 100) / 100.0 : 0);
            teacherDetailList.add(detail);
        }

        teacherDetailList.sort((a, b) -> ((Comparable) b.get("periods")).compareTo(a.get("periods")));

        List<Map<String, Object>> dayDetailList = new ArrayList<>();
        String[] days = {"周一", "周二", "周三", "周四", "周五", "周六", "周日"};
        for (String day : days) {
            Map<String, Object> detail = new HashMap<>();
            detail.put("day", day);
            detail.put("count", dayCount.getOrDefault(day, 0));
            dayDetailList.add(detail);
        }

        model.addAttribute("totalSchedules", filteredSchedules.size());
        model.addAttribute("totalTeachers", uniqueTeachers.size());
        model.addAttribute("totalClasses", uniqueClasses.size());
        model.addAttribute("totalCourses", uniqueCourses.size());
        model.addAttribute("courseCount", courseCount);
        model.addAttribute("classCount", classCount);
        model.addAttribute("teacherCount", teacherCount);
        model.addAttribute("dayCount", dayCount);
        model.addAttribute("classroomCount", classroomCount);
        model.addAttribute("periodCount", periodCount);
        model.addAttribute("teacherDetailList", teacherDetailList);
        model.addAttribute("dayDetailList", dayDetailList);
        model.addAttribute("courses", courses);
        model.addAttribute("clazzes", clazzes);
        model.addAttribute("query", query);

        return "report/scheduleReport";
    }
}
