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
import jakarta.mvc.Models;
import jakarta.mvc.Controller;
import jakarta.mvc.binding.BindingResult;
import jakarta.mvc.binding.MvcBinding;
import jakarta.mvc.binding.ParamError;
import jakarta.validation.Valid;
import jakarta.validation.executable.ExecutableType;
import jakarta.validation.executable.ValidateOnExecution;
import jakarta.ws.rs.BeanParam;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;


/**
 * Functions and views the for the reviews
 */
@Path("/review")
@Controller
@Singleton
@Lock(LockType.READ)
@Validate
public class ReviewController {

    @Inject
    private Models models;

    @Inject
    private BindingResult bindingResult;

    @Inject
    private ErrorProps errorProps;

    @Inject
    private Messages messages;

    @EJB
    private ReviewService reviewService;


    @GET
    @Path("new")
    public String newReview() {
        return "new.jsp";
    }

    @GET
    @Path("/view/{id : \\d+}")
    public String view(@PathParam("id") final Long id) {
        addReviewToModel(id);
        final Review review = reviewService.findById(id);
        return "view.jsp";
    }

    @GET
    @Path("/list")
    public String getList() {

        models.put("results", reviewService.listAll());

        return "list.jsp";
    }

    @GET
    @Path("/edit/{id : \\d+}")
    public String edit(@PathParam("id") final Long id) {
        addReviewToModel(id);
        return "edit.jsp";
    }


    @GET
    @Path("/remove/{id : \\d+}")
    public String delete(@PathParam("id") final Long id) {
        final Review review = reviewService.findById(id);
        reviewService.delete(review);

        return "redirect:/review/list";
    }

    private void addReviewToModel(final Long id) {
        final Review review = reviewService.findById(id);
        models.put("review", Utils.createFormFromReview(review));
        models.put("reviewId", id);
    }

    @POST
    @Path("/save")
    @ValidateOnExecution(type = ExecutableType.NONE)
    public String edit(@FormParam("id") Long id, @BeanParam @MvcBinding @Valid @ValidReview ReviewForm form) {

        if (bindingResult.isFailed()) {

            bindingResult.getAllErrors().stream()
                    .map(ParamError::getMessage)
                    .forEach(messages::addError);

            // The inputs should be populated with the previously submitted invalid values
            models.put("review", form);
            models.put("reviewId", id);
            models.put("messages", messages);
            models.put("errorProps", errorProps.getProperties());
            return "edit.jsp";
        } else {

            Review review = reviewService.findById(id);
            Utils.update(review, form);
            review = reviewService.merge(review);

            models.put("review", Utils.createFormFromReview(review));
            models.put("reviewId", review.getId());
            return "view.jsp";
        }
    }
}
