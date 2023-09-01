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

package org.acmecorp.review.entity;

import jakarta.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "Review.listAll", query = "select r from Review r"),
    @NamedQuery(name = "Review.findById", query = "select r from Review r where r.id = :id"),

})

@Table(name = "review")
public class Review {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100)
    private String firstname;
    @Column(length = 100)
    private String lastname;
    @Column(length = 100)
    private String email;
    @Column(length = 100)
    private String dob;
    @Column(length = 100)
    private String moviename;
    @Column(length = 100)
    private String directorname;
    @Column(length = 100)
    private int movierating;
    @Column(length = 65535)
    @Lob
    private String notes;


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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

    public void setNotes(final String notes) {
        this.notes = notes;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(final String dob) {
        this.dob = dob;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(final String moviename) { this.moviename = moviename; }

    public String getDirectorname() {
        return directorname;
    }

    public void setDirectorname(final String directorname) {
        this.directorname = directorname;
    }

    public int getMovierating() { return movierating; }

    public void setMovierating(final int movierating) { this.movierating = movierating; }



}
