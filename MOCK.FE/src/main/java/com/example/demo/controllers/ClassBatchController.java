package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.config.ApiPath;
import com.example.demo.services.AdminService;
import com.example.demo.services.BudgetService;
import com.example.demo.services.ClasssBatchService;
import com.example.demo.services.DeliveryTypeService;
import com.example.demo.services.FormatTypeService;
import com.example.demo.services.LocationService;
import com.example.demo.services.ScopeService;
import com.example.demo.services.SubSubjectTypeService;
import com.example.demo.services.SubjectTypeService;
import com.example.demo.services.SupplierPartnerService;
import com.example.demo.services.TrainerService;
import com.example.demo.services.dtos.BudgetDto;
import com.example.demo.services.dtos.ClassAdminDto;
import com.example.demo.services.dtos.ClassBatchDto;
import com.example.demo.services.dtos.DeliveryTypeDto;
import com.example.demo.services.dtos.FormatTypeDto;
import com.example.demo.services.dtos.LocationDto;
import com.example.demo.services.dtos.ScopeDto;
import com.example.demo.services.dtos.SubSubjectTypeDto;
import com.example.demo.services.dtos.SubjectTypeDto;
import com.example.demo.services.dtos.SupplierPartnerDto;
import com.example.demo.services.dtos.TrainerDto;
import com.example.demo.services.dtos.enums.StatusClassEnum;

@Controller
@RequestMapping(value = ApiPath.API_DOMAIN + ApiPath.CLASSBATCH_DOMAIN)
public class ClassBatchController {

	@Autowired
	ClasssBatchService classsBatchService;

	@Autowired
	LocationService locationService;

	@Autowired
	BudgetService budgetService;

	@Autowired
	DeliveryTypeService deliveryTypeService;

	@Autowired
	FormatTypeService formatTypeService;

	@Autowired
	ScopeService scopeService;

	@Autowired
	AdminService adminService;

	@Autowired
	SubjectTypeService subjectTypeService;

	@Autowired
	SubSubjectTypeService subSubjectTypeService;

	@Autowired
	SupplierPartnerService supplierPartnerService;

	@Autowired
	TrainerService trainerService;

	@GetMapping(value = "")
	public ModelAndView get(ModelMap model) {

		return new ModelAndView("redirect:/api/v1/class_batch/page/1");
	}

	@GetMapping(value = "/page/{currentPage}")
	public ModelAndView getAll(ModelMap model, @PathVariable(name = "currentPage") int currentPage,
			@ModelAttribute("success") String success) {
		System.out.println(currentPage);
		System.out.println("co vo day");
		List<ClassBatchDto> dtos = classsBatchService.getAll();

		List<ClassBatchDto> pagingdto = classsBatchService.getAllPaging(currentPage);
		model.addAttribute("list_class_batch", pagingdto);

		int totalPage = dtos.size() / 4 + 1;
		model.addAttribute("totalPage", totalPage);

		List<LocationDto> locationDtos = locationService.getAll();
		model.addAttribute("list_location", locationDtos);

		return new ModelAndView("fe_class_list", model);

	}
	@GetMapping(value = "/update/{id}")
	public ModelAndView getOne(ModelMap model, @PathVariable("id") Integer id, @ModelAttribute("error") String error) {

		ClassBatchDto dto = classsBatchService.getOne(id);
		model.addAttribute("class_batch", dto);

		List<LocationDto> locationDtos = locationService.getAll();
		model.addAttribute("list_location", locationDtos);

		List<BudgetDto> budgetDtos = budgetService.getAll();
		model.addAttribute("list_budget", budgetDtos);

		List<DeliveryTypeDto> deliveryTypeDtos = deliveryTypeService.getAll();
		model.addAttribute("list_delivery", deliveryTypeDtos);

		List<FormatTypeDto> formatTypeDtos = formatTypeService.getAll();
		model.addAttribute("list_format", formatTypeDtos);

		List<ScopeDto> scopeDtos = scopeService.getAll();
		model.addAttribute("list_scope", scopeDtos);

		List<ClassAdminDto> classAdminDtos = adminService.getAll();
		model.addAttribute("list_admin", classAdminDtos);

		List<SubjectTypeDto> subjectTypeDtos = subjectTypeService.getAll();
		model.addAttribute("list_subject", subjectTypeDtos);

		List<SubSubjectTypeDto> subSubjectTypeDtos = subSubjectTypeService.getAll();
		model.addAttribute("list_subsubject", subSubjectTypeDtos);

		List<SupplierPartnerDto> supplierPartnerDtos = supplierPartnerService.getAll();
		model.addAttribute("list_supplier", supplierPartnerDtos);

		List<TrainerDto> trainerDtos = trainerService.getAll();
		model.addAttribute("list_trainer", trainerDtos);

		model.addAttribute("totalCountBudgetUpdate", classsBatchService.getOne(id).getBudgetItems().size());
		model.addAttribute("totalCountAuditUpdate", classsBatchService.getOne(id).getAudit().size());
		
		return new ModelAndView("fe_class_profile_update", model);
	}

	@GetMapping(value = "/detail_class/{id}")
	public ModelAndView getDetailClassBatch(ModelMap model, @PathVariable("id") Integer id) {

		ClassBatchDto dto = classsBatchService.getOne(id);
		model.addAttribute("class_batch", dto);

		List<LocationDto> locationDtos = locationService.getAll();
		model.addAttribute("list_location", locationDtos);

		List<BudgetDto> budgetDtos = budgetService.getAll();
		model.addAttribute("list_budget", budgetDtos);

		List<DeliveryTypeDto> deliveryTypeDtos = deliveryTypeService.getAll();
		model.addAttribute("list_delivery", deliveryTypeDtos);

		List<FormatTypeDto> formatTypeDtos = formatTypeService.getAll();
		model.addAttribute("list_format", formatTypeDtos);

		List<ScopeDto> scopeDtos = scopeService.getAll();
		model.addAttribute("list_scope", scopeDtos);

		List<ClassAdminDto> classAdminDtos = adminService.getAll();
		model.addAttribute("list_admin", classAdminDtos);

		List<SubjectTypeDto> subjectTypeDtos = subjectTypeService.getAll();
		model.addAttribute("list_subject", subjectTypeDtos);

		List<SubSubjectTypeDto> subSubjectTypeDtos = subSubjectTypeService.getAll();
		model.addAttribute("list_subsubject", subSubjectTypeDtos);

		List<SupplierPartnerDto> supplierPartnerDtos = supplierPartnerService.getAll();
		model.addAttribute("list_supplier", supplierPartnerDtos);

		List<TrainerDto> trainerDtos = trainerService.getAll();
		model.addAttribute("list_trainer", trainerDtos);

		return new ModelAndView("fe_class_profile_view");
	}

	@GetMapping(value = "/create")
	public ModelAndView create(ModelMap model, @ModelAttribute("error") String error) {

		List<LocationDto> locationDtos = locationService.getAll();
		model.addAttribute("list_location", locationDtos);

		List<BudgetDto> budgetDtos = budgetService.getAll();
		model.addAttribute("list_budget", budgetDtos);

		List<DeliveryTypeDto> deliveryTypeDtos = deliveryTypeService.getAll();
		model.addAttribute("list_delivery", deliveryTypeDtos);

		List<FormatTypeDto> formatTypeDtos = formatTypeService.getAll();
		model.addAttribute("list_format", formatTypeDtos);

		List<ScopeDto> scopeDtos = scopeService.getAll();
		model.addAttribute("list_scope", scopeDtos);

		List<ClassAdminDto> classAdminDtos = adminService.getAll();
		model.addAttribute("list_admin", classAdminDtos);

		List<SubjectTypeDto> subjectTypeDtos = subjectTypeService.getAll();
		model.addAttribute("list_subject", subjectTypeDtos);

		List<SubSubjectTypeDto> subSubjectTypeDtos = subSubjectTypeService.getAll();
		model.addAttribute("list_subsubject", subSubjectTypeDtos);

		List<SupplierPartnerDto> supplierPartnerDtos = supplierPartnerService.getAll();
		model.addAttribute("list_supplier", supplierPartnerDtos);

		List<TrainerDto> trainerDtos = trainerService.getAll();
		model.addAttribute("list_trainer", trainerDtos);

		ClassBatchDto dto = new ClassBatchDto();
		model.addAttribute("class_batch", dto);

		return new ModelAndView("fe_class_profile_create", model);

	}

	@PostMapping(value = "/create")
	public ModelAndView create(ModelMap model, @ModelAttribute ClassBatchDto dto, RedirectAttributes redirectAttributes) {
		Boolean checkCreate = classsBatchService.create(dto);
		if(!checkCreate)
		{
			redirectAttributes.addFlashAttribute("error", "Create class batch fail!");
			return new ModelAndView("redirect:/api/v1/class_batch/create");
		}
		redirectAttributes.addFlashAttribute("success", "Create class batch success!");
		return new ModelAndView("redirect:/api/v1/class_batch/page/1");
	}

	@GetMapping(value = "/filter")
	public ModelAndView getFilter(ModelMap model, HttpServletRequest request) {
		List<ClassBatchDto> classBatchDtos = classsBatchService.getFilter(request.getParameter("searchClassName"),
				request.getParameter("searchLocationName"), request.getParameter("searchActualStartName"),
				request.getParameter("searchActualEndName"), request.getParameter("searchStatus"));
		model.addAttribute("list_class_batch", classBatchDtos);

		List<LocationDto> locationDtos = locationService.getAll();
		model.addAttribute("list_location", locationDtos);

		model.addAttribute("success", "Filter success!");
		return new ModelAndView("fe_class_list", model);
	}

	@PostMapping("/update")
	public ModelAndView update(ModelMap model, @ModelAttribute ClassBatchDto dto, RedirectAttributes redirectAttributes) {
		Boolean checkUpdate = classsBatchService.update(dto, dto.getClassBatchId());
		if(!checkUpdate)
		{
			redirectAttributes.addFlashAttribute("error", "Update class batch fail!");
			return new ModelAndView("redirect:/api/v1/class_batch/update/"+dto.getClassBatchId());
		}
		redirectAttributes.addFlashAttribute("success", "Update class batch success!");
		return new ModelAndView("redirect:/api/v1/class_batch/page/1");
	}

	@GetMapping(value = "/delete/{id}")
	public ModelAndView delete(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {

		classsBatchService.delete(id);
		redirectAttributes.addFlashAttribute("success", "Delete class batch success!");
		return new ModelAndView("redirect:/api/v1/class_batch/page/1");
	}

	@PostMapping(value = "/edit")
	public ModelAndView update(ModelMap model, @ModelAttribute ClassBatchDto dto,
			@RequestParam(value = "action", required = true) String action, RedirectAttributes redirectAttributes) {
		if (action.equals("update")) {
			return new ModelAndView("redirect:/api/v1/class_batch/update/" + dto.getClassBatchId());
		}
		if (action.equals("submit")) {
			if (dto.getStatus() == StatusClassEnum.DRAFT) {
				dto.setStatus(StatusClassEnum.Submitted);
				dto.setCurriculumn("123");
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to Submitted successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error", "You change status class to Submitted fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}
		if (action.equals("start")) {
			if (dto.getStatus() == StatusClassEnum.Planned) {
				dto.setStatus(StatusClassEnum.In_progress);
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to In progress successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error",
						"You change status class to In progress fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}

		if (action.equals("finish")) {
			if (dto.getStatus() == StatusClassEnum.In_progress) {
				dto.setStatus(StatusClassEnum.Pending_for_review);
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to Pending for review successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error",
						"You change status class to Pending for review fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}
		if (action.equals("cancel")) {
			if (dto.getStatus() == StatusClassEnum.DRAFT || dto.getStatus() == StatusClassEnum.Submitted) {
				dto.setStatus(StatusClassEnum.Cancelled);
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to Cancelled successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error",
						"You change status class to Cancelled fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}
		if (action.equals("approve")) {
			if (dto.getStatus() == StatusClassEnum.Submitted) {
				dto.setStatus(StatusClassEnum.Planning);
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to Planning successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error",
						"You change status class to Planning fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}

		if (action.equals("reject")) {
			if (dto.getStatus() == StatusClassEnum.Submitted) {
				dto.setStatus(StatusClassEnum.Rejected);
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to Rejected successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error",
						"You change status class to Rejected fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}
		if (action.equals("close")) {
			if (dto.getStatus() == StatusClassEnum.Pending_for_review) {
				dto.setStatus(StatusClassEnum.Closed);
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to Closed successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error",
						"You change status class to Closed fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}
		if (action.equals("request")) {
			if (dto.getStatus() == StatusClassEnum.Pending_for_review) {
				dto.setStatus(StatusClassEnum.Waiting_for_more_information);
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to Waiting for more information successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error",
						"You change status class to Waiting for more information fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}
		if (action.equals("accept")) {
			if (dto.getStatus() == StatusClassEnum.Planning) {
				dto.setStatus(StatusClassEnum.Accepted);
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to Accepted successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error",
						"You change status class to Accepted fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}
		if (action.equals("decline")) {
			if (dto.getStatus() == StatusClassEnum.Planning) {
				dto.setStatus(StatusClassEnum.Declined);
				classsBatchService.updateStatus(dto, dto.getClassBatchId());
				redirectAttributes.addFlashAttribute("success",
						"You change status class to Declined successfully!");
			} else {
				redirectAttributes.addFlashAttribute("error",
						"You change status class to Declined fail!");
				return new ModelAndView("redirect:/api/v1/class_batch/detail_class/" + dto.getClassBatchId());
			}
		}
		return new ModelAndView("redirect:/api/v1/class_batch/page/1");
	}
}
