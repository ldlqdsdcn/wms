package com.delmar.cargo.model;

import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name b_production_line
  * Date:2016-08-29 15:01:00
  **/
@Data
public class ProductionLine extends CoreModel {

private Integer id;
private String productName;
private Integer amount;
private Integer productionId;
private String batch;

}