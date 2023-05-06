package project.ecom.se2_backup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.ecom.se2_backup.dto.DiscountDto;
import project.ecom.se2_backup.model.Discount;
import project.ecom.se2_backup.repository.DiscountRepository;

import java.util.Optional;

@Service
public class DiscountService {
    @Autowired
    DiscountRepository discountRepository;

    public void createDiscount(DiscountDto discountDto){
        Discount discount = new Discount();
        discount.setDiscount(discountDto.getDiscount());
    }

    public void deleteDiscount(DiscountDto discountDto, Long discountId) throws Exception{
        Optional<Discount> optionalDiscount = discountRepository.findById(discountId);
        if(optionalDiscount.isEmpty()){
            throw new Exception("No discount presents");
        }
        Discount discount = optionalDiscount.get();
        discountRepository.delete(discount);
    }

}
