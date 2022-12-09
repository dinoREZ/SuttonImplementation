// Copyright 2022 Peter Braun
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.fhws.fiw.fds.suttondemo.models;

import java.time.LocalDate;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import com.owlike.genson.annotation.JsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.utils.JsonDateTimeConverter;
import de.fhws.fiw.fds.sutton.utils.XmlDateTimeConverter;

public class PersonClientModel extends AbstractClientModel {
    private String firstName;
    private String lastName;

    @XmlJavaTypeAdapter(XmlDateTimeConverter.class)
    private LocalDate birthDate = LocalDate.of(1970, 1, 1);
    private String emailAddress;

    public PersonClientModel() {}

    public PersonClientModel(final String firstname, final String lastname,
            final String emailAddress, final LocalDate birthdate) {
        this.firstName = firstname;
        this.lastName = lastname;
        this.birthDate = birthdate;
        this.emailAddress = emailAddress;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    @JsonConverter(JsonDateTimeConverter.class)
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @JsonConverter(JsonDateTimeConverter.class)
    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(final String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", firstName='" + firstName + '\'' + ", lastName='"
                + lastName + '\'' + ", birthDate=" + birthDate + ", emailAddress='" + emailAddress
                + '\'' + '}';
    }
}
