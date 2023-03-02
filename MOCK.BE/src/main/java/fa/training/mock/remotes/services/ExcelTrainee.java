package fa.training.mock.remotes.services;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.multipart.MultipartFile;

import fa.training.mock.remotes.entities.ClassBatch;
import fa.training.mock.remotes.entities.Faculty;
import fa.training.mock.remotes.entities.Status;
import fa.training.mock.remotes.entities.Trainee;
import fa.training.mock.remotes.entities.TraineeCandidateProfile;
import fa.training.mock.remotes.entities.University;
import fa.training.mock.remotes.repositories.ClassBatchRepository;
import fa.training.mock.remotes.repositories.FacultyRepository;
import fa.training.mock.remotes.repositories.StatusRepository;
import fa.training.mock.remotes.repositories.TraineeCandidateProfileRepository;
import fa.training.mock.remotes.repositories.TraineeRepository;
import fa.training.mock.remotes.repositories.UniversityRepository;
import fa.training.mock.ultils.Common;

@Service
public class ExcelTrainee {
	@Autowired
	TraineeRepository traineeRepository;

	@Autowired
	TraineeCandidateProfileRepository candidateProfileRepository;

	@Autowired
	UniversityRepository universityRepository;

	@Autowired
	FacultyRepository facultyRepository;

	@Autowired
	StatusRepository statusRepository;

	@Autowired
	ClassBatchRepository classBatchRepository;

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Trainee> trainees;

	public static boolean hasExcelFormat(MultipartFile file) {

		return true;
	}

	public ExcelTrainee() {
		super();
	}

	public ExcelTrainee(List<Trainee> trainees) {
		this.trainees = trainees;
		workbook = new XSSFWorkbook();
	}

	private void writeHeaderLine() {
		sheet = workbook.createSheet("Trainee");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "Trainee id", style);
		createCell(row, 1, "Account", style);
		createCell(row, 2, "FullName", style);
		createCell(row, 3, "Birthday", style);
		createCell(row, 4, "Gender", style);
		createCell(row, 5, "University", style);
		createCell(row, 6, "Faculty", style);
		createCell(row, 7, "Phone", style);
		createCell(row, 8, "Email", style);
		createCell(row, 9, "Skill", style);
		createCell(row, 10, "GraduationYear", style);
		createCell(row, 11, "ForeignLanguage", style);
		createCell(row, 12, "Level", style);
		createCell(row, 13, "Type", style);
		createCell(row, 14, "Allowcation status", style);
		createCell(row, 15, "Remarks", style);
		createCell(row, 16, "Commited value", style);
		createCell(row, 17, "Committed working duration", style);
		createCell(row, 18, "Trainee group", style);
		createCell(row, 19, "Remarks", style);
		createCell(row, 20, "Committed working start date", style);
		createCell(row, 21, "TPBank account", style);
		createCell(row, 22, "Status name", style);
	}

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Trainee t : trainees) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			TraineeCandidateProfile p = t.getTraineeCandidateProfile();
			createCell(row, columnCount++, String.valueOf(t.getTraineeId()), style);
			createCell(row, columnCount++, p.getAccount(), style);
			createCell(row, columnCount++, p.getFullName(), style);
			createCell(row, columnCount++, String.valueOf(p.getDateOfBirth()), style);
			createCell(row, columnCount++, p.getGender(), style);
			createCell(row, columnCount++, p.getUniversity().getUniversityName(), style);
			createCell(row, columnCount++, p.getFaculty().getMajorName(), style);
			createCell(row, columnCount++, p.getPhone(), style);
			createCell(row, columnCount++, p.getEmail(), style);
			createCell(row, columnCount++, p.getSkill(), style);
			createCell(row, columnCount++, String.valueOf(p.getGraduationYear()), style);
			createCell(row, columnCount++, p.getForeignLanguage(), style);
			createCell(row, columnCount++, String.valueOf(p.getLevel()), style);
			createCell(row, columnCount++, p.getType(), style);
			createCell(row, columnCount++, String.valueOf(p.getAllowcationStatus()), style);
			createCell(row, columnCount++, p.getRemarks(), style);
			createCell(row, columnCount++, String.valueOf(t.getCommitedValue()), style);
			createCell(row, columnCount++, String.valueOf(t.getCommittedWorkingDuration()), style);
			createCell(row, columnCount++, t.getTraineeGroup(), style);
			createCell(row, columnCount++, t.getRemarks(), style);
			createCell(row, columnCount++, String.valueOf(t.getCommittedWorkingStartDate()), style);
			createCell(row, columnCount++, t.getTpBankAccount(), style);
			createCell(row, columnCount++, t.getStatus().getStatusName(), style);
		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}

	public List<Trainee> excelToTutorials(InputStream is, Integer classId) {
		System.out.println("Vao day");
		try {
			Workbook workbook = WorkbookFactory.create(is);
			Sheet sheet = workbook.getSheetAt(0);

			Iterator<Row> rows = sheet.iterator();

			List<Trainee> trainees = new ArrayList<>();

			int rowNumber = 0;
			while (rows.hasNext()) {
				Row currentRow = rows.next();

				// skip header
				if (rowNumber == 0) {
					rowNumber++;
					continue;
				}

				Iterator<Cell> cellsInRow = currentRow.iterator();
				Trainee trainee = new Trainee();
				TraineeCandidateProfile profile = new TraineeCandidateProfile();
				University university = new University();
				Faculty faculty = new Faculty();
				Status status = new Status();
				System.out.println("cell 24 = " + currentRow.getCell(24));
				System.out.println("cell first = " + currentRow.getFirstCellNum());
				System.out.println("cell last = " + currentRow.getLastCellNum());
				
				while (cellsInRow.hasNext()) {

					Cell currentCell = cellsInRow.next();
					String cellValue = currentCell.getStringCellValue();
					if (cellValue.trim().equals(""))
						continue;
					switch (currentCell.getColumnIndex()) {
					case 0:
						trainee.setTraineeId(Integer.valueOf(cellValue));
						break;

					case 1:
						profile.setAccount(cellValue);
						break;

					case 2:
						profile.setFullName(cellValue);
						break;
					case 3:
						profile.setDateOfBirth(Common.convertToDate(cellValue));
						break;
					case 4:
						profile.setGender(cellValue);
						break;
					case 5:
						university.setUniversityName(cellValue);
						break;
					case 6:
						faculty.setMajorName(cellValue);
						break;
					case 7:
						profile.setPhone(cellValue);
						break;
					case 8:
						profile.setEmail(cellValue);
						break;
					case 9:
						profile.setSkill(cellValue);
						break;
					case 10:
						profile.setGraduationYear(Common.convertToDate(cellValue));
						break;
					case 11:
						profile.setForeignLanguage(cellValue);
						break;
					case 12:
						profile.setLevel(Integer.parseInt(cellValue));
						break;
					case 13:
						profile.setType(cellValue);
						break;
					case 14:
						profile.setAllowcationStatus(Boolean.valueOf(cellValue));
						break;
					case 15:
						profile.setRemarks(cellValue);
						break;
					case 16:
						trainee.setCommitedValue(Double.valueOf(cellValue));
						break;
					case 17:
						trainee.setCommittedWorkingDuration(Integer.valueOf(cellValue));
						break;
					case 18:
						trainee.setTraineeGroup(cellValue);
						break;
					case 19:
						trainee.setRemarks(cellValue);
						break;
					case 20:
						trainee.setCommittedWorkingStartDate(Common.convertToDate(cellValue));
						break;
					case 21:
						trainee.setTpBankAccount(cellValue);
						break;
					case 22:
						System.out.println("==================" + cellValue + "=============");
						status.setStatusName(cellValue);
						break;
					default:
						break;
					}

				}
				System.out.println(trainee);
				System.out.println(profile);
				System.out.println(university);
				System.out.println(faculty);
				System.out.println(status);
				System.out.println("-------------");

				if (profile.getAccount() != null && university.getUniversityName() != null
						&& faculty.getMajorName() != null && status.getStatusName() != null) {
					
					List<University> oldUniversities = universityRepository
							.findByUniversityName(university.getUniversityName());
					University oldUniversity = oldUniversities.size() == 0 ? universityRepository.save(university)
							: oldUniversities.get(0);

					List<Faculty> oldFaculties = facultyRepository.findByMajorName(faculty.getMajorName());
					Faculty oldFaculty = oldFaculties.size() == 0 ? facultyRepository.save(faculty)
							: oldFaculties.get(0);

					Trainee oldTrainee = traineeRepository.findById(trainee.getTraineeId()).orElse(null);

					if (oldTrainee != null) {
						TraineeCandidateProfile oldProfile = oldTrainee.getTraineeCandidateProfile();
						profile.setProfileId(oldProfile.getProfileId());
						profile.setCv(oldProfile.getCv());
					}
					profile.setUniversity(oldUniversity);
					profile.setFaculty(oldFaculty);

					candidateProfileRepository.save(profile);

					List<Status> oldStatuses = statusRepository.findByStatusName(status.getStatusName());
					Status oldStatus = oldStatuses.size() == 0 ? statusRepository.save(status) : oldStatuses.get(0);

					trainee.setStatus(oldStatus);
					trainee.setTraineeCandidateProfile(profile);
					trainee.setClassBatch(classBatchRepository.findById(classId).get());

					traineeRepository.save(trainee);
				}
			}
			System.out.println("Ket thuc");
			workbook.close();
			return trainees;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
		}
	}
}