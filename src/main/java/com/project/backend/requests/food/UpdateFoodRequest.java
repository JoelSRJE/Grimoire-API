package com.project.backend.requests.food;

import java.util.List;

public record UpdateFoodRequest(
        List<UpdateFoodRequestObject> foods
) {
}
