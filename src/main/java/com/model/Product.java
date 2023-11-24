package com.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="category_id", referencedColumnName = "category_id")
	private Category category;
	private double price;
	private double weight;
	private String description;
	private String imageName;
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Product(int id, Category category, String name, double price, double weight, String description,
			String imageName) {
		super();
		this.id = id;
		this.category = category;
		this.name = name;
		this.price = price;
		this.weight = weight;
		this.description = description;
		this.imageName = imageName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImageName() {
		return imageName;
	}
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", category=" + category + ", name=" + name + ", price=" + price + ", weight="
				+ weight + ", description=" + description + ", imageName=" + imageName + "]";
	}
}
