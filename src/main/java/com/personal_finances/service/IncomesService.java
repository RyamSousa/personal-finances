package com.personal_finances.service;

import com.personal_finances.exceptions.BusinessException;
import com.personal_finances.model.Accounts;
import com.personal_finances.model.Categories;
import com.personal_finances.model.Incomes;
import com.personal_finances.repository.IncomesRepository;
import com.personal_finances.utils.GetDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.personal_finances.utils.MessagesExceptions.*;

@Service
@Transactional
@RequiredArgsConstructor
public class IncomesService {

    private final IncomesRepository incomesRepository;

    private final CategoriesService categoriesService;

    private final AccountsService accountsService;

    public Incomes save(Incomes income){

        Accounts account = accountsService.findByAccountNumber(income.getAccount().getAccountNumber());

        Categories category = categoriesService.findById(income.getCategory().getId());

        incomesRepository.save(income);

        return income;
    }

    public Incomes update(Incomes income){

        Accounts account = accountsService.findByAccountNumber(income.getAccount().getAccountNumber());

        Categories category = categoriesService.findById(income.getCategory().getId());

        incomesRepository.save(income);

        return income;
    }

    public Incomes delete(Long id){
        Incomes income = this.findById(id);

        incomesRepository.deleteById(id);

        return income;
    }

    public Incomes findById(Long id){
        Optional<Incomes> optionalIncome = incomesRepository.findById(id);

        if (optionalIncome.isEmpty()) {
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return optionalIncome.get();
    }

    public List<Incomes> findByCategory(Long id){
        List<Optional<Incomes>> optionalIncomes = incomesRepository.findByCategory(id);

        if (optionalIncomes.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return optionalIncomes.stream()
                .map(incomes -> incomes.get())
                .collect(Collectors.toList());
    }

    public List<Incomes> findAllIncomes(){

        List<Incomes> lst = incomesRepository.findAll();

        if (lst.isEmpty()){
            throw new BusinessException(NO_RECORDS_FOUND);
        }

        return lst;
    }

    public List<Incomes> findIncomesByDate(Long id, String date){

        date = GetDate.extractMonthAndYear(date);

        List<Optional<Incomes>> lst = incomesRepository.findIncomesByDate(id, date);

        return lst.stream().map(incomes -> incomes.get()).collect(Collectors.toList());
    }

}
