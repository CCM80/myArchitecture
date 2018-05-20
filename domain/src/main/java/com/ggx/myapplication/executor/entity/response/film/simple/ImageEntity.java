package com.ggx.myapplication.executor.entity.response.film.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by ggx on 2018/5/19.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageEntity {
    String large;
    String medium;
    String small;
}