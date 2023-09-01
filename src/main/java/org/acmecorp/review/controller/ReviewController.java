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

package org.acmecorp.review.controller;

import org.acmecorp.review.cdi.Validate;
import org.acmecorp.review.entity.Review;
import org.acmecorp.review.jpa.ReviewService;
import org.acmecorp.review.model.ReviewForm;
import org.acmecorp.review.model.Utils;
import org.acmecorp.review.validation.ErrorProps;
import org.acmecorp.review.validation.ValidReview;
import org.acmecorp.review.validation.Messages;

import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.mvc.Models;
import javax.mvc.Controller;
import javax.mvc.binding.BindingResult;
import javax.mvc.binding.MvcBinding;
import javax.mvc.binding.ParamError;
import javax.validation.Valid;
import javax.validation.executable.ExecutableType;
import javax.validation.executable.ValidateOnExecution;
import javax.ws.rs.BeanParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;


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
