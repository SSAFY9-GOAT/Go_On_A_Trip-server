package com.ssafy.goat.controller;

//import com.ssafy.goat.trend.dto.TrendViewDto;
import com.ssafy.goat.controller.response.TrendViewDto;
import com.ssafy.goat.trend.service.TrendService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/index")
public class IndexController extends HttpServlet {

    private final TrendService trendService;

    @GetMapping("")
    @ApiOperation(value = "트랜드 불러오기")
    public Map<String, Object> goIndex(){
        TrendViewDto teenage = trendService.popularByTeenage();
        TrendViewDto twenty = trendService.popularByTwenty();
        TrendViewDto thirty = trendService.popularByThirty();
        TrendViewDto male = trendService.popularByMale();
        TrendViewDto female = trendService.popularByFemale();

//        model.addAttribute("teenage", teenage);
//        model.addAttribute("twenty", twenty);
//        model.addAttribute("thirty", thirty);
//        model.addAttribute("male", male);
//        model.addAttribute("female", female);

        Map<String, Object> result = new HashMap<>();
        result.put("teenage", teenage);
        result.put("twenty", twenty);
        result.put("thirty", thirty);
        result.put("male", male);
        result.put("female", female);

        return result;
    }

//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        TrendViewDto teenage = trendService.popularByTeenage();
//        TrendViewDto twenty = trendService.popularByTwenty();
//        TrendViewDto thirty = trendService.popularByThirty();
//        TrendViewDto male = trendService.popularByMale();
//        TrendViewDto female = trendService.popularByFemale();
//
//        request.setAttribute("teenage", teenage);
//        request.setAttribute("twenty", twenty);
//        request.setAttribute("thirty", thirty);
//        request.setAttribute("male", male);
//        request.setAttribute("female", female);
//
//        request.getRequestDispatcher("/index.jsp").forward(request, response);
//    }
}
