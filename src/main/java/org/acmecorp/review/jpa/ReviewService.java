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

package org.acmecorp.review.jpa;

import org.acmecorp.review.entity.Review;
import org.tomitribe.hodao.*;
import org.tomitribe.hodao.impl.PersistenceHandler;

import jakarta.ejb.Lock;
import jakarta.ejb.LockType;
import jakarta.ejb.Singleton;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.List;

@Singleton
@Lock(LockType.READ)
public abstract class ReviewService implements InvocationHandler {
    @Inject
    // note the use of @Inject and not @PersistenceContext here. This uses the PersistenceProducer bean instead.
    private EntityManager em;


    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        return PersistenceHandler.invoke(this.em, method, args);
    }

    public Review persist(Review review) {

        return doPersist(review);
    }

    @Persist
    protected abstract Review doPersist(final Review review);

    @NamedQuery(value = "Review.listAll")
    public abstract List<Review> listAll();


    @NamedQuery(value = "Review.findById")
    public abstract Review findById(@QueryParam("id") final Long id);

    @Merge
    protected abstract Review doMerge(final Review review);

    @Remove
    public abstract void delete(final Review review);

    public Review merge(final Review review) {
        return doMerge(review);
    }


}
