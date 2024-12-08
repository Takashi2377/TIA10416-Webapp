package com.coupon.controller;

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import com.coupon.model.*;

public class CouponServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");

		if ("getOne_For_Display".equals(action)) { // 來自select_page.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			String str = req.getParameter("cop_id");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入優惠券ID");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			Integer cop_id = null;
			try {
				cop_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("優惠券ID格式不正確");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 2.開始查詢資料 *****************************************/
			CouponService couSvc = new CouponService();
			CouponVO couponVO = couSvc.getOneCoupon(cop_id);
			if (couponVO == null) {
				errorMsgs.add("查無資料");
			}
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/select_page.jsp");
				failureView.forward(req, res);
				return;// 程式中斷
			}

			/*************************** 3.查詢完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couponVO", couponVO); // 資料庫取出的couponVO物件,存入req
			String url = "/back-end/coupon/listOneCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("getOne_For_Update".equals(action)) { // 來自listAllEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ****************************************/
			Integer cop_id = Integer.valueOf(req.getParameter("cop_id"));

			/*************************** 2.開始查詢資料 ****************************************/
			CouponService couSvc = new CouponService();
			CouponVO couponVO = couSvc.getOneCoupon(cop_id);

			/*************************** 3.查詢完成,準備轉交(Send the Success view) ************/
			req.setAttribute("couponVO", couponVO); // 資料庫取出的empVO物件,存入req
			String url = "/back-end/coupon/update_coupon_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 成功轉交 update_emp_input.jsp
			successView.forward(req, res);
		}

		if ("update".equals(action)) { // 來自update_coupon_input.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 - 輸入格式的錯誤處理 **********************/
			Integer cop_id = Integer.valueOf(req.getParameter("cop_id").trim());

			String cop_code = req.getParameter("cop_code");
			String cop_codeReg = "^[a-zA-Z0-9]{2,20}$";
			if (cop_code == null || cop_code.trim().length() == 0) {
				errorMsgs.add("優惠碼: 請勿空白");
			} else if (!cop_code.trim().matches(cop_codeReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("優惠碼: 只能是英文字母、數字, 且長度必需在2到20之間");
			}

//			java.sql.Date crt_date = null;
//			try {
//				crt_date = java.sql.Date.valueOf(req.getParameter("crt_date").trim());
//			} catch (IllegalArgumentException e) {
//				crt_date = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入建立日期!");
//			}
			
			java.sql.Date end_date = null;
			try {
				end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
			} catch (IllegalArgumentException e) {
				end_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入有效日期!");
			}

			Integer discount = null;
			try {
				discount = Integer.valueOf(req.getParameter("discount").trim());
			} catch (NumberFormatException e) {
				discount = 0;
				errorMsgs.add("折扣金額請填數字.");
			}

			CouponVO couponVO = new CouponVO();
			couponVO.setCop_id(cop_id);
			couponVO.setCop_code(cop_code);
//			couponVO.setCrt_date(crt_date);
			couponVO.setEnd_date(end_date);
			couponVO.setDiscount(discount);
			
			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的couponVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/update_coupon_input.jsp");
				failureView.forward(req, res);
				return; // 程式中斷
			}

			/*************************** 2.開始修改資料 *****************************************/
			CouponService couSvc = new CouponService();
			couponVO = couSvc.updateCoupon(cop_code,end_date, discount, cop_id);

			/*************************** 3.修改完成,準備轉交(Send the Success view) *************/
			req.setAttribute("couponVO", couponVO); // 資料庫update成功後,正確的的empVO物件,存入req
			String url = "/back-end/coupon/listOneCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 修改成功後,轉交listOneEmp.jsp
			successView.forward(req, res);
		}

		if ("insert".equals(action)) { // 來自addEmp.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*********************** 1.接收請求參數 - 輸入格式的錯誤處理 *************************/
			String cop_code = req.getParameter("cop_code");
			String cop_codeReg = "^[a-zA-Z0-9]{2,20}$";
			if (cop_code == null || cop_code.trim().length() == 0) {
				errorMsgs.add("優惠碼: 請勿空白");
			} else if (!cop_code.trim().matches(cop_codeReg)) { // 以下練習正則(規)表示式(regular-expression)
				errorMsgs.add("優惠碼: 只能是英文字母、數字, 且長度必需在2到20之間");
			}

//			String job = req.getParameter("job").trim();
//			if (job == null || job.trim().length() == 0) {
//				errorMsgs.add("職位請勿空白");
//			}

//			java.sql.Date crt_date = null;
//			try {
//				crt_date = java.sql.Date.valueOf(req.getParameter("crt_date").trim());
//			} catch (IllegalArgumentException e) {
//				crt_date = new java.sql.Date(System.currentTimeMillis());
//				errorMsgs.add("請輸入建立日期!");
//			}
			
			java.sql.Date end_date = null;
			try {
				end_date = java.sql.Date.valueOf(req.getParameter("end_date").trim());
			} catch (IllegalArgumentException e) {
				end_date = new java.sql.Date(System.currentTimeMillis());
				errorMsgs.add("請輸入有效日期!");
			}

			Integer discount = null;
			try {
				discount = Integer.valueOf(req.getParameter("discount").trim());
			} catch (NumberFormatException e) {
				discount = 0;
				errorMsgs.add("折扣金額請填數字.");
			}

//			Double comm = null;
//			try {
//				comm = Double.valueOf(req.getParameter("comm").trim());
//			} catch (NumberFormatException e) {
//				comm = 0.0;
//				errorMsgs.add("獎金請填數字.");
//			}

//			Integer deptno = Integer.valueOf(req.getParameter("deptno").trim());

			CouponVO couponVO = new CouponVO();
			couponVO.setCop_code(cop_code);
//			couponVO.setCrt_date(crt_date);
			couponVO.setEnd_date(end_date);
			couponVO.setDiscount(discount);

			// Send the use back to the form, if there were errors
			if (!errorMsgs.isEmpty()) {
				req.setAttribute("couponVO", couponVO); // 含有輸入格式錯誤的couponVO物件,也存入req
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/coupon/addCoupon.jsp");
				failureView.forward(req, res);
				return;
			}

			/*************************** 2.開始新增資料 ***************************************/
			CouponService couSvc = new CouponService();
			couponVO = couSvc.addCoupon(cop_code, end_date, discount);

			/*************************** 3.新增完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/coupon/listAllCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllEmp.jsp
			successView.forward(req, res);
		}

		if ("delete".equals(action)) { // 來自listAllCoupon.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);

			/*************************** 1.接收請求參數 ***************************************/
			Integer cop_id = Integer.valueOf(req.getParameter("cop_id"));

			/*************************** 2.開始刪除資料 ***************************************/
			CouponService couSvc = new CouponService();
			couSvc.deleteCoupon(cop_id);

			/*************************** 3.刪除完成,準備轉交(Send the Success view) ***********/
			String url = "/back-end/coupon/listAllCoupon.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}

}