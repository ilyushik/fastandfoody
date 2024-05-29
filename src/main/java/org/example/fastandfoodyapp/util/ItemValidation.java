package org.example.fastandfoodyapp.util;

import org.example.fastandfoodyapp.Model.Item;
import org.example.fastandfoodyapp.Repositories.ItemRepository;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ItemValidation implements Validator {

    private final ItemRepository itemRepository;

    public ItemValidation(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Item item = (Item) target;
//        try {
//            itemRepository.findByItem_name(item.getItem_name());
//            errors.rejectValue("item_name", "", "Такий товар вже існує");
//        } catch (Exception e) {
//            return;
//        }

        if (itemRepository.findByItem_name(item.getItem_name()) != null) {
            errors.rejectValue("item_name", "", "Такий товар вже існує");
        }
    }
}
