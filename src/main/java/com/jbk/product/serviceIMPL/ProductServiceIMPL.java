package com.jbk.product.serviceIMPL;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jbk.product.dao.CategoryDao;
import com.jbk.product.dao.ProductDao;
import com.jbk.product.dao.SupplierDao;
import com.jbk.product.entity.Category;
import com.jbk.product.entity.Product;
import com.jbk.product.entity.Supplier;
import com.jbk.product.model.Charges;
import com.jbk.product.model.FinalProduct;
import com.jbk.product.service.ProductService;
import com.jbk.product.utility.SheetUtility;

@Service
public class ProductServiceIMPL implements ProductService {

	@Autowired
	ProductDao productDao;
	@Autowired
	SheetUtility sheetUtility;
	@Autowired
	CategoryDao categorydao;
	@Autowired
	SupplierDao supplierDao;

	@Override
	public boolean saveProduct(Product product) {
		boolean isAdded = productDao.saveProduct(product);
		return isAdded;
	}

	@Override
	public Product getProductById(long productId) {
		Product product = productDao.getProductById(productId);
		return product;
	}

	@Override
	public List<Product> getAllProducts() {
		List<Product> list = productDao.getAllProducts();
		return list;
	}

	@Override
	public boolean deleteProductById(long productId) {
		boolean isDeleted = productDao.deleteProductById(productId);
		return isDeleted;
	}

	@Override
	public boolean updateProduct(Product product) {
		boolean isUpdated = productDao.updateProduct(product);
		return isUpdated;
	}

	@Override
	public List<Product> sortProductById_ASC() {

		return productDao.sortProductById_ASC();
	}

	@Override
	public List<Product> sortProductById_DESC() {
		return productDao.sortProductById_DESC();
	}

	@Override
	public List<Product> getMaxPriceProducts() {

		return productDao.getMaxPriceProducts();
	}

	@Override
	public double countSumOfProductPrice() {
		return productDao.countSumOfProductPrice();
	}

	@Override
	public int getTotalCountOfProducts() {
		return productDao.getTotalCountOfProducts();
	}

	@Override
	public FinalProduct getfinalProductById(long id) {
		FinalProduct finalProduct = new FinalProduct();
		try {
			Product product = productDao.getProductById(id);
			System.out.println(product);

			int gst = product.getCategory().getGst();
			double gstAmount = ((product.getProductPrice() * gst) / 100);
			double discount = product.getCategory().getDiscount();
			double discountAmount = ((product.getProductPrice() * discount) / 100);
			double deliveryCharge = product.getCategory().getDeliveryCharge();
			Charges charges = new Charges();
			charges.setGst(gst);
			charges.setDeliveryCharge(deliveryCharge);
			double finalProductPrice = ((product.getProductPrice() + gstAmount + deliveryCharge) - discountAmount);
			finalProduct.setProductId(id);
			finalProduct.setProductName(product.getProductName());
			finalProduct.setCategory(product.getCategory());
			finalProduct.setSupplier(product.getSupplier());
			finalProduct.setProductPrice(product.getProductPrice());
			finalProduct.setProductQTY(product.getProductQTY());
			finalProduct.setFinalProductPrice(product.getProductPrice());
			finalProduct.setCharges(charges);
			finalProduct.setDiscountAmount(discountAmount);
			finalProduct.setGstAmount(gstAmount);
			finalProduct.setFinalProductPrice(finalProductPrice);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalProduct;

	}

	@Override
	public void fileWriting(MultipartFile file) {

		boolean isExcelFile = sheetUtility.checkFileFormat(file);
		String path = sheetUtility.setPathOfFile(file);

		try {
			FileOutputStream fos = new FileOutputStream(new File(path));
			byte[] data = file.getBytes();
			fos.write(data);
			readSheet(path);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public List<Product> readSheet(String path) {
		List<Product> list = new ArrayList<>();
		try {
			Product product = null;

			FileInputStream fis = new FileInputStream(new File(path));
			Workbook workbook = new XSSFWorkbook(path);
			Sheet sheet = workbook.getSheetAt(0);
			Iterator<Row> rows = sheet.rowIterator();

			while (rows.hasNext()) {

				Row row = rows.next();
				if (row.getRowNum() == 0) {
					continue;
				}
				product = new Product();
				String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new java.util.Date());
				long id = Long.parseLong(timeStamp);
				product.setProductId(id);
				Iterator<Cell> cells = row.cellIterator();
				while (cells.hasNext()) {
					Cell cell = cells.next();
					int count = cell.getColumnIndex();
					switch (count) {
					case 0:
						String productName = cell.getStringCellValue();
						product.setProductName(productName);
						count++;
						break;
					case 1:
						long supplierId = (long) cell.getNumericCellValue();
						Supplier supplier = supplierDao.getSupplierById(supplierId);
						product.setSupplier(supplier);
						count++;
						break;
					case 2:
						long categoryId = (long) cell.getNumericCellValue();
						Category category = categorydao.getCategoryById(categoryId);
						product.setCategory((category));
						count++;
						break;
					case 3:
						double productQty = cell.getNumericCellValue();
						product.setProductQTY((int) productQty);
						count++;
						break;
					case 4:
						double productPrice = cell.getNumericCellValue();
						product.setProductPrice(productPrice);
						count++;
						break;
					}
				}
				list.add(product);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(list);
		return list;

	}

}
