package com.ssafy.goat.controller.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AddTripPlanRequest {
    @NotEmpty
    @Size(max = 100)
    private String title;
    @NotEmpty
    @Size(max=1000)
    private List<String> tripPlanIdList;

}
