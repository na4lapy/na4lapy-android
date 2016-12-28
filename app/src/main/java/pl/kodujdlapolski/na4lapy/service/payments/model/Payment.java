/*
 *	Copyright 2017 Stowarzyszenie Na4Łapy
 *
 *	Licensed under the Apache License, Version 2.0 (the "License");
 *	you may not use this file except in compliance with the License.
 *	You may obtain a copy of the License at
 *
 *	http://www.apache.org/licenses/LICENSE-2.0
 *
 *	Unless required by applicable law or agreed to in writing, software
 *	distributed under the License is distributed on an "AS IS" BASIS,
 *	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *	See the License for the specific language governing permissions and
 *	limitations under the License.
 */
package pl.kodujdlapolski.na4lapy.service.payments.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import pl.kodujdlapolski.na4lapy.service.payments.model.type.PaymentType;

@Data
public class Payment {

    @SerializedName("sale")
    private Sale sale;

    @SerializedName("customer")
    private Customer customer;

    @SerializedName("back_url")
    private String backUrl;

    @SerializedName("payment_type")
    private PaymentType paymentType;
}
