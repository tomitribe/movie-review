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


import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.ws.rs.FormParam;
import java.io.Serializable;

@Named("reviewform")
@SessionScoped
public class ReviewForm implements Serializable {

    @FormParam("firstname")
    private String firstname;

    @FormParam("lastname")
    private String lastname;

    @FormParam("dob")
    private String dob;

    @FormParam("email")
    private String email;

    @FormParam("moviename")
    private String moviename;

    @FormParam("directorname")
    private String directorname;

    @FormParam("movierating")
    @Min(1)
    @Max(10)
    private int movierating;

    @FormParam("notes")
    private String notes;


    public ReviewForm() {
    }

    public void copy (final ReviewForm source) {
        this.setFirstname(source.getFirstname());
        this.setLastname(source.getLastname());
        this.setDob(source.getDob());
        this.setEmail(source.getEmail());
        this.setNotes(source.getNotes());
        this.setMoviename(source.getMoviename());
        this.setDirectorname(source.getDirectorname());
        this.setMovierating(source.getMovierating());

    }



    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(final String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(final String lastname) {
        this.lastname = lastname;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(final String notes) { this.notes = notes; }

    public String getDob() {
        return dob;
    }

    public void setDob(final String dob) {
        this.dob = dob;
    }

    public String getMoviename() { return moviename; }

    public void setMoviename(final String moviename) { this.moviename = moviename; }

    public void setMovierating(final int movierating) { this.movierating = movierating; }

    public int getMovierating() { return movierating; }

    public String getDirectorname() {
        return directorname;
    }

    public void setDirectorname(final String directorname) { this.directorname = directorname; }



    public void clear() {
        this.setFirstname(null);
        this.setLastname(null);
        this.setDob(null);
        this.setEmail(null);
        this.setNotes(null);
        this.setDirectorname(null);
        this.setMoviename(null);
        this.setMovierating(0);

    }
}
