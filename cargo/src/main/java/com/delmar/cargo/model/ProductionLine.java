/**
* 版权所有 刘大磊 2013-07-01
* 作者：刘大磊
* 电话：13336390671
* email:ldlqdsd@126.com
*/
package com.delmar.cargo.model;

import com.delmar.core.model.CoreModel;
import lombok.Data;
 /**
  * table name b_production_line
  * Date:2016-09-13 11:28:19
  **/
@Data
public class ProductionLine extends CoreModel {

private String productName;
private Integer amount;
private Integer productionId;
private String batch;

}