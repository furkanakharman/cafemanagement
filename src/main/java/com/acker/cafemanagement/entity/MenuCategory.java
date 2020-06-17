package com.acker.cafemanagement.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class MenuCategory {
	
		private Long id;
		public String categoryName;
		public String categoryDescription;
		@ApiModelProperty(position = 1,required=false)
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		@ApiModelProperty(position = 2,required=true,value="Desserts")
		public String getCategoryName() {
			return categoryName;
		}
		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}
		@ApiModelProperty(position = 3,required=true,value="they are sweet?")
		public String getCategoryDescription() {
			return categoryDescription;
		}
		public void setCategoryDescription(String categoryDescription) {
			this.categoryDescription = categoryDescription;
		}
		
	
}
