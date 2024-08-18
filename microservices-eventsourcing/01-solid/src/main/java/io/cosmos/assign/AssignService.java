package io.cosmos.assign;

import java.util.*;

import java.util.ArrayList;
import java.util.List;

public class AssignService {

    public List<Customer> assign(List<Customer> customers,
                                 List<Surveyor> surveyors) {
        List<Customer> filteredCustomers = new ArrayList<>();

        for (Customer customer : customers) {
            if (this.calculateAge(customer) >= 30) {
                filteredCustomers.add(customer);
            }
        }

        filteredCustomers.sort(new Comparator<Customer>() {
            @Override
            public int compare(Customer customer1, Customer customer2) {
                return calculateAge(customer1).compareTo(calculateAge(customer2));
            }
        });

        int i=0;
        for (Customer customer : filteredCustomers) {
            Surveyor surveyor = surveyors.get(i % surveyors.size());
            customer.setSurveyorId(surveyor.getId());
            i++;
        }

        return filteredCustomers;
    }

    public Integer calculateAge(Customer customer) {
        String year = customer.getBirthday().substring(0, 4);
        String month = customer.getBirthday().substring(5, 6);
        String day = customer.getBirthday().substring(7, 8);

        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.parseInt(year));
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(day));

        Date birthDate = calendar.getTime();

        Calendar birthdayCalendar = Calendar.getInstance();
        birthdayCalendar.setTime(birthDate);
        Calendar todayCalendar = Calendar.getInstance();

        return todayCalendar.get(Calendar.YEAR) - birthdayCalendar.get(Calendar.YEAR);
    }
}
