/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.acmecorp.review.model;

import org.acmecorp.review.entity.Review;

import java.text.SimpleDateFormat;

public class Utils {

    private static SimpleDateFormat UK_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");

    public static Review toNewReview(final ReviewForm form) {
        final Review review = new Review();

        set(review, form);
        return review;
    }

    private static void set(final Review review, final ReviewForm form) {

        review.setNotes(form.getNotes());
        review.setFirstname(form.getFirstname());
        review.setLastname(form.getLastname());
        review.setDob(form.getDob());
        review.setEmail(form.getEmail());
        review.setMoviename(form.getMoviename());
        review.setDirectorname(form.getDirectorname());
        review.setMovierating(form.getMovierating());

    }

    private static Long convert(final String input) {
        try {
            return Long.parseLong(input);
        } catch (final Exception e) {
            return null;
        }
    }

    public static void update(final Review review, final ReviewForm form) {

        set(review, form);
    }

    public static ReviewForm createFormFromReview(final Review review) {
        final ReviewForm form = new ReviewForm();


        if (review != null) {
            form.setNotes(review.getNotes());
            form.setFirstname(review.getFirstname());
            form.setLastname(review.getLastname());
            form.setDob(review.getDob());
            form.setEmail(review.getEmail());
            form.setMoviename(review.getMoviename());
            form.setDirectorname(review.getDirectorname());
            form.setMovierating(review.getMovierating());
        }

        return form;
    }


    public static void copy(ReviewForm dest, ReviewForm source) {
        dest.setDob(source.getDob());
        dest.setEmail(source.getEmail());
        dest.setFirstname(source.getFirstname());
        dest.setLastname(source.getLastname());
        dest.setNotes(source.getNotes());
        dest.setMoviename(source.getMoviename());
        dest.setDirectorname(source.getDirectorname());
        dest.setMovierating(source.getMovierating());

    }
}
