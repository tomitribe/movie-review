/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.acmecorp.review.validation;

import jakarta.inject.Inject;
import jakarta.validation.ConstraintValidatorContext;

public class BaseValidator {
    @Inject
    private ErrorProps errorProps;

    protected boolean check(final boolean condition, final String errorMessage, final ConstraintValidatorContext context, final String field) {
        if (! condition) {
            errorProps.addProperty(field);
            context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
        }

        return condition;
    }
}
