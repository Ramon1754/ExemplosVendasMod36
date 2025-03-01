/*
 * Copyright 2013-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.springdata.multistore.shop;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * An entity representing an {@link Order}. Note how we don't need any MongoDB mapping annotations as {@code id} is
 * recognized as the id property by default.
 *
 * @author Thomas Darimont
 * @author Oliver Gierke
 */
@Data
//@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@Document
public class Order {

    private String id;
    private String customerId;
    private Date orderDate;
    private List<LineItem> items;

    public Order(String customerId, Date orderDate, List<LineItem> items) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.items = items;
    }

    /**
     * Creates a new {@link Order} for the given customer id and order date.
     *
     * @param customerId
     * @param orderDate
     */
    public Order(String customerId, Date orderDate) {
        this(customerId, orderDate, new ArrayList<>());
    }

    public Order(String customerId, Date orderDate, ArrayList items) {
        this.customerId = customerId;
        this.orderDate = orderDate;
        this.items = items;
    }

    /**
     * Adds a {@link LineItem} to the {@link Order}.
     *
     * @param item
     * @return
     */
    public Order addItem(LineItem item) {

        this.items.add(item);
        return this;
    }
}