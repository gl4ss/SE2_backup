package project.ecom.se2_backup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.ecom.se2_backup.common.ApiResponse;
import project.ecom.se2_backup.dto.DiscountDto;
import project.ecom.se2_backup.service.DiscountService;


@RestController
@RequestMapping("/discount")
public class DiscountController {
    @Autowired
    DiscountService discountService;


    @PostMapping(value = "/add")
    public ResponseEntity<ApiResponse> createDiscount(@RequestBody DiscountDto discountDto){
        discountService.createDiscount(discountDto);
        return new ResponseEntity<>(new ApiResponse(true, "Added"), HttpStatus.CREATED);
    }

    @PostMapping(value = "/delete/{discountId}")
    public ResponseEntity<ApiResponse> deleteDiscount(@PathVariable(value = "discountId") Long discountId,
                                                      @RequestBody DiscountDto discountDto) throws Exception{
        discountService.deleteDiscount(discountDto, discountId);
        return new ResponseEntity<>(new ApiResponse(true, "Deleted"), HttpStatus.OK);
    }

}
