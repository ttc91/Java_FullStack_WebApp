package fa.training.mock.controllers;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fa.training.mock.config.ApiPath;
import fa.training.mock.remotes.services.ExcelTrainee;
import fa.training.mock.remotes.services.TraineeService;

@RestController
@RequestMapping(ApiPath.apiDomain + ApiPath.CLASSBATCHDOMAIN + "/excel/{classId}")
public class ExcelController {

	@Autowired
	TraineeService traineeService;
	
	@Autowired
	ExcelTrainee excelTrainee;

	@GetMapping
	public void exportFile(HttpServletResponse response) {

		try {
			String headerKey = "Content-Disposition";
			String headerValue = "attachment; filename=Trainee_.xlsx";
			response.setHeader(headerKey, headerValue);

			ExcelTrainee excelTrainee = new ExcelTrainee(traineeService.findAll());
			excelTrainee.export(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@PostMapping
	public void importFile(@RequestParam("file") MultipartFile file, @PathVariable("classId") Integer classId) {
		String message = "";

		if (ExcelTrainee.hasExcelFormat(file)) {
			try {
				excelTrainee.excelToTutorials(file.getInputStream(), classId);
				message = "Uploaded the file successfully: " + file.getOriginalFilename();
			} catch (Exception e) {
				System.out.println(e);
				message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			}
			System.out.println(message);
		}

	}

}
