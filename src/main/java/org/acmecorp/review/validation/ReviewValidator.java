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

package org.acmecorp.review.validation;

import org.acmecorp.review.model.ReviewForm;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewValidator extends BaseValidator implements ConstraintValidator<ValidReview, ReviewForm> {

    @Override
    public void initialize(ValidReview validReview) {

    }

    @Override
    public boolean isValid(final ReviewForm value, final ConstraintValidatorContext context) {
        // turn off the default
        context.disableDefaultConstraintViolation();

        boolean valid = true;

        valid = check(!StringUtils.isEmpty(value.getFirstname()), "First name is required", context, "firstname") && valid;
        valid = check(!StringUtils.isEmpty(value.getLastname()), "Lastname is required", context, "lastname") && valid;
        valid = check(!StringUtils.isEmpty(value.getMoviename()), "Movie Name is required", context, "moviename") && valid;

        valid = check(value.getDob() != null && value.getDob().trim().length() > 0, "Date of birth is required", context, "dob") && valid;

        // check DOB is within the last 200 years
        try {
            final Date date = new SimpleDateFormat("dd/MM/yyyy").parse(value.getDob());
            final Date now = new Date();
            valid = check(date.getTime() < now.getTime(), "Date of birth should not be in the future", context, "dob") && valid;
            valid = check((now.getTime() - date.getTime()) < (1000L * 60L * 60L * 24L * 365L * 200L), "Date of birth is a long way in the past. Has it been input with four digits for the year? dd/mm/yyyy", context, "dob") && valid;
        } catch (Exception e) {
            valid = check(false, "Date of birth should be in the format dd/mm/yyyy", context, "dob");
        }

        return valid;
    }

}
