package com.example.dbeaver.compare_events;

import com.example.dbeaver.compare_events.entity.EmbeddedTags;
import com.example.dbeaver.compare_events.entity.ResponseTag;
import com.example.dbeaver.compare_events.entity.Tag;
import com.example.dbeaver.criteria.Criteria;
import com.example.dbeaver.criteria.conditions.GreaterThanCondition;
import com.example.dbeaver.entity.account.Account;
import com.example.dbeaver.repository.AccountCriteriaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("compare")
public class CompareController {
    private final AccountCriteriaRepository repository;
    private final TemplateRestClient restClient;
    private final Set<String> eventsFromDatabase;
    private final Set<String> eventsFromCRM;
    public CompareController(AccountCriteriaRepository repository, TemplateRestClient restClient) {
        this.repository = repository;
        this.restClient = restClient;
        Criteria<Account> criteria = new Criteria<>();
        LocalDate date = LocalDate.of(2024, Month.MARCH, 1);
        setDateInCriteria(date, criteria);
        eventsFromDatabase = repository.findAll(criteria).parallelStream()
                .map(Account::getUsrOldEvents)
                .map(events -> events.split(" "))
                .flatMap(Arrays::stream)
                .filter(name -> name.contains("/"))
                .collect(Collectors.toSet());

        List<ResponseTag> tagsList = new ArrayList<>(11);
        for (int i = 1; i < 12; i++) {
            tagsList.add(restClient.request(i));
        }
        eventsFromCRM = tagsList.parallelStream()
                .map(ResponseTag::_embedded)
                .map(EmbeddedTags::tags)
                .flatMap(List::stream)
                .map(Tag::name)
                .filter(this::filter)
                .collect(Collectors.toSet());
    }
    private boolean filter(String name) {
        if (!name.contains("/")) {
            return false;
        }
        for (char ch : name.toCharArray()) {
            if (Character.isLetter(ch)) {
                return true;
            }
        }
        return false;
    }
    @GetMapping("crm")
    public List<String> inCRM() {
        Set<String> eventsInCRM = new HashSet<>(eventsFromCRM);
        eventsInCRM.removeAll(eventsFromDatabase);
        return eventsInCRM.stream().sorted().toList();
    }
    @GetMapping("database")
    public List<String> inDatabase() {
        Set<String> eventsInDatabase = new HashSet<>(eventsFromDatabase);
        eventsInDatabase.removeAll(eventsFromCRM);
        return eventsInDatabase.stream().sorted().collect(Collectors.toList());
    }
    @GetMapping("database/source")
    public List<String> databaseEvents(@RequestParam(name = "date", defaultValue = "01-03-2024", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        Criteria<Account> criteria = new Criteria<>();
        setDateInCriteria(date, criteria);
        return repository.findAll(criteria).parallelStream()
                .map(Account::getUsrOldEvents)
                .distinct()
                .toList();
    }

    private void setDateInCriteria(LocalDate date, Criteria<?> criteria) {
        criteria.addCondition(new GreaterThanCondition<>("createdOn", LocalDateTime.of(date, LocalTime.of(0, 0))));
    }
}
