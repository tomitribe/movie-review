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

package org.acmecorp.review.controller;

import org.acmecorp.review.cdi.Validate;
import org.acmecorp.review.entity.Review;
import org.acmecorp.review.jpa.ReviewService;
import org.acmecorp.review.model.ReviewForm;
import org.acmecorp.review.model.Utils;
import org.acmecorp.review.validation.ErrorProps;
import org.acmecorp.review.validation.ValidReview;
import org.acmecorp.review.validation.Messages;

import jakarta.ejb.EJB;
import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.MvcBinding;
import jakarta.mvc.binding.ParamError;
import jakarta.validation.Valid;
import jakarta.validation.executable.ExecutableType;
import jakarta.validation.executable.ValidateOnExecution;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;


/**
 * Functions and views the for the review selection
 */
@Path("/home")
@Controller
@Singleton
@Lock(LockType.READ)
@Validate
public class HomeController {

    @Inject
    private Models models;

    @Inject
    private ReviewForm reviewForm;

    @Inject
    private BindingResult bindingResult;
    
    @Inject
    private ErrorProps errorProps;

    @Inject
    private Messages messages;

    @EJB
    private ReviewService reviewService;

    @GET
    public String newReview() {
        this.reviewForm.clear();
        return "reviewselect.jsp";
    }



    @POST
    @Path("/select")
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String select() {
        if (bindingResult.isFailed()) {
            messages.addError("Please select the service required.");
            models.put("messages", messages);
            models.put("reviewForm", reviewForm); // shouldn't need this!

            return "reviewselect.jsp";
        } else {

            models.put("messages", messages);
            models.put("reviewForm", reviewForm); // shouldn't need this!

            return "new.jsp";
        }
    }


    @POST
    @Path("/create")
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String createItem(@BeanParam @MvcBinding @Valid @ValidReview ReviewForm form) {

        if (bindingResult.isFailed()) {

            bindingResult.getAllErrors().stream()
                    .map(ParamError::getMessage)
                    .forEach(messages::addError);

            reviewForm.copy(form);

            // The inputs should be populated with the previously submitted invalid values
            models.put("messages", messages);
            models.put("errorProps", errorProps.getProperties());
            models.put("reviewForm", reviewForm); // shouldn't need this!

        } else {

            final Review newReview = Utils.toNewReview(form);

            final Review review = reviewService.persist(newReview);
            models.put("review", newReview);

            // store in the session
            Utils.copy(this.reviewForm, form);

            return "submitted.jsp";
        }

        return "new.jsp";
    }
}

