package com.project.backend.requests.food;

import java.util.List;

public record DeleteFoodsRequest(
        List<String> foodIds
) {
}
