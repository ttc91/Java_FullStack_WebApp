fetch('http://localhost:8080/api/v1/budget/get_all')
	.then((response) => response.json())
	.then((data) => data.objList.forEach(function(item) {


		if (parseInt($("#budget-code").val()) === item.id) {
			$("#budget-total").val(item.total);
			$("#over-budget").val(item.overBudget)
		}

	}));

$("#files").change(function() {
	filename = this.files[0].name;
	const box = document.getElementById('fileName');

	// ✅ Change (replace) the text of the element
	box.textContent = filename;
	document.getElementById("textFileName").value = filename;
});


$("#endDate").change(function() {
	var startDate = document.getElementById("startDate").value;
	var endDate = document.getElementById("endDate").value;
	if ((Date.parse(startDate) >= Date.parse(endDate))) {
		alert("End date must be greater than start date!");
		document.getElementById("endDate").value = "";
	}
})
$("#endDateExpected").change(function() {
	var startDateExpected = document.getElementById("startDateExpected").value;
	var endDateExpected = document.getElementById("endDateExpected").value;
	if ((Date.parse(startDateExpected) >= Date.parse(endDateExpected))) {
		alert("End date expected must be greater than start date expected!");
		document.getElementById("endDateExpected").value = "";
	}
})
$("#endDateActual").change(function() {
	var startDateActual = document.getElementById("startDateActual").value;
	var endDateActual = document.getElementById("endDateActual").value;
	if ((Date.parse(startDateActual) >= Date.parse(endDateActual))) {
		alert("End date actual must be greater than start date actual!");
		document.getElementById("endDateActual").value = "";
	}
})

$(document).ready(function() {


	var readURL = function(input) {
		if (input.files && input.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('.avatar').attr('src', e.target.result);
			}

			reader.readAsDataURL(input.files[0]);
		}
	}


	$(".file-upload").on('change', function() {
		readURL(this);
	});

});

$("#budget-code").on("change", function(e) {

	fetch('http://localhost:8080/api/v1/budget/get_all')
		.then((response) => response.json())
		.then((data) => data.objList.forEach(function(item) {


			if (parseInt($("#budget-code").val()) === item.id) {
				$("#budget-total").val(item.total);
				$("#over-budget").val(item.overBudget)
			}

		}));

})

let i = 0;
$("#btn-budget-create").on("click", function(event) {
	$("#add-budget").append(`<div class="input-row" id="budget-row-${i}"> 
                          <div class="page-class-input-format input-form" style="width: 100%; border: 1px solid;">
                          <button type="button" class="btn btn-outline page-btn-class"id="btn-budget-create-${i}" onClick="removeBudget(${i})" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color: rgb(240, 238, 236); color: red;"> <i class="fa-solid fa-trash-can"></i></button>
                          <div class="flex-row-container none-border-top">                        							
                          								<input type="text" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "budgetItems[${i}].itemName" required/>
														<input type="text" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "budgetItems[${i}].unit" required/>
														<input onchange="myChangeBudget(${i})" type="number" style="flex: 1; height: 100%; "
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "unitExpense${i}" name = "budgetItems[${i}].unitExpense" min="0" required/>
														<input onchange="myChangeBudget(${i})" type="number" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "quantity${i}" name = "budgetItems[${i}].quantity" min="0" required/>
														<input onchange="myChangeBudget(${i})" type="number" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "amount${i}" readonly/>
														<input onchange="myChangeBudget(${i})" type="number" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "tax${i}" name = "budgetItems[${i}].tax" required/>
														<input onchange="myChangeBudget(${i})" type="number" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius sumBudget" id = "sum${i}" readonly/>
														<input type="text" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "budgetItems[${i}].note"/>
													</div></div></div>`)
	i++;
})

function removeBudget(i) {
	$('#budget-row-' + i).remove();
}


function myChangeBudget(i) {
	var budgetUnitValue = document.getElementById('unitExpense' + i).value;
	var budgetQuantityValue = document.getElementById('quantity' + i).value;
	document.getElementById('amount' + i).value = budgetUnitValue * budgetQuantityValue;
	var budgetAmountValue = document.getElementById('amount' + i).value;
	var budgetTaxValue = document.getElementById('tax' + i).value;
	document.getElementById('sum' + i).value = Number(budgetAmountValue) + Number((budgetAmountValue * (budgetTaxValue * 0.01)));
	var budgetAmountValue = document.getElementsByClassName('sumBudget');
	var length = budgetAmountValue.length;
	var budgetUnitValue = document.getElementById('budget-total').value;
	var totalBudgetItem = 0;
	for (var a = 0; a < length; a++) {
		totalBudgetItem += budgetAmountValue[a].value;
	}
	console.log(totalBudgetItem);
	if (Number(totalBudgetItem) >= Number(budgetUnitValue)) {
		document.getElementById('over-budget').value = 'true';
	} else {
		document.getElementById('over-budget').value = 'false';
	}
}

let j = 0;
$("#btn-audit-create").on("click", function(event) {
	j++;
	$("#add-audit").append(`<div class="input-row"' + 'id="audit-row-${j}">
		<div class="page-class-input-format input-form" style="width: 100%; border: 1px solid;">
		<button type="button" class="btn btn-outline page-btn-class"id="btn-audit-create-${j}" onClick="removeAudit(${j})" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color: rgb(240, 238, 236); color: red;"> <i class="fa-solid fa-trash-can"></i></button>
		<div class="flex-row-container none-border-top">
		<input type="date" style="flex: 1; height: 100%; class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${j}].date" required/>
		<select style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${j}].eventCategory" required>
		<option value="Trainer">Trainer</option>
		<option value="Trainee">Trainee</option>
		<option value="Courseware">Courseware</option>
		<option value="Organization">Organization</option>
		<option value="Logistics">Logistics</option>
		<option value="Management">Management</option>
		<option value="Calendar">Calendar</option>
		<option value="Others">Others</option>
		</select>
		<input type="text" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${j}].relatedPeople" required/>
		<input type="text" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${j}].action" required/>
		<input type="text" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${j}].pic" required/>
		<input type="date" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${j}].dealine" required/>
		<input type="text" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${j}].note" required/>
		</div></div></div>`)
})
function removeAudit(j) {
	$('#audit-row-' + j).remove();
}





$("#btn-budget-update").on("click", function(event) {
	let TotalCountBudgetItem = document.getElementById('totalCountBudgetUpdate').value;
	$("#add-budget").append(`<div class="input-row" id="budget-row-${TotalCountBudgetItem}"> 
                          <div class="page-class-input-format input-form" style="width: 100%; border: 1px solid;">
                          <button type="button" class="btn btn-outline page-btn-class"id="btn-budget-create-${TotalCountBudgetItem}" onClick="removeBudgetUpdate(${TotalCountBudgetItem})" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color: rgb(240, 238, 236); color: red;"> <i class="fa-solid fa-trash-can"></i></button>
                          <div class="flex-row-container none-border-top">   
                          								<input type="hidden" id="budgetItems${TotalCountBudgetItem}" value="0"/>                     							
                          								<input type="text" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "budgetItems[${TotalCountBudgetItem}].itemName" required/>
														<input type="text" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "budgetItems[${TotalCountBudgetItem}].unit" required/>
														<input onchange="myChangeBudget(${TotalCountBudgetItem})" type="number" style="flex: 1; height: 100%; "
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "unitExpense${TotalCountBudgetItem}" name = "budgetItems[${TotalCountBudgetItem}].unitExpense" required/>
														<input onchange="myChangeBudget(${TotalCountBudgetItem})" type="number" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "quantity${TotalCountBudgetItem}" name = "budgetItems[${TotalCountBudgetItem}].quantity" required/>
														<input type="number" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "amount${TotalCountBudgetItem}" readonly/>
														<input onchange="myChangeBudget(${TotalCountBudgetItem})" type="number" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "tax${TotalCountBudgetItem}" name = "budgetItems[${TotalCountBudgetItem}].tax" required/>
														<input type="number" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius sumBudget" id = "sum${TotalCountBudgetItem}" readonly/>
														<input type="text" style="flex: 1; height: 100%;"
															class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "budgetItems[${TotalCountBudgetItem}].note" required/>
													</div></div></div>`);
	TotalCountBudgetItem++;
})

function removeBudgetUpdate(i) {
	var idBudgetItem = document.getElementById('budgetItems' + i).value;
	if (idBudgetItem != 0) {
		if (confirm("Do Want to delete this budget item?")) {
		fetch('http://localhost:8080/api/v1/budget_item/delete/' + idBudgetItem, {
			method: 'DELETE',
		})
			.then(res => res.text()) // or res.json()
			.then(res => console.log(res))
	document.getElementById('budgetItems' + i).value = "";
	document.getElementById('itemName' + i).value = "";
	document.getElementById('unit' + i).value = "";
	document.getElementById('unitExpense' + i).value = "";
	document.getElementById('quantity' + i).value = "";
	document.getElementById('amount' + i).value = "";
	document.getElementById('tax' + i).value = "";
	document.getElementById('sum' + i).value = "";
	document.getElementById('noteBudget' + i).value = "";
	}
	}else{
		$('#budget-row-' + i).remove();
	}

}





$("#btn-audit-update").on("click", function(event) {
	let TotalCountAuditItem = document.getElementById('totalCountAuditUpdate').value;
	$("#add-audit").append(`<div class="input-row" id="audit-row-${TotalCountAuditItem}"> 
                          <div class="page-class-input-format input-form" style="width: 100%; border: 1px solid;">
                          <button type="button" class="btn btn-outline page-btn-class"id="btn-audit-create-${TotalCountAuditItem}" onClick="removeAuditUpdate(${TotalCountAuditItem})" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="background-color: rgb(240, 238, 236); color: red;"> <i class="fa-solid fa-trash-can"></i></button>
                         <div class="flex-row-container none-border-top">
                          								<input type="hidden" id="auditId${TotalCountAuditItem}" value="0"/>                     							
                          								<input onchange="validationTimeAudit(${TotalCountAuditItem})" type="date" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "dateAudit${TotalCountAuditItem}" name = "audit[${TotalCountAuditItem}].date" required/>
														<select style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${TotalCountAuditItem}].eventCategory" required>
														<option value="Trainer">Trainer</option>
														<option value="Trainee">Trainee</option>
														<option value="Courseware">Courseware</option>
														<option value="Organization">Organization</option>
														<option value="Logistics">Logistics</option>
														<option value="Management">Management</option>
														<option value="Calendar">Calendar</option>
														<option value="Others">Others</option>
														</select>
														<input type="text" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${TotalCountAuditItem}].relatedPeople" required/>
														<input type="text" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${TotalCountAuditItem}].action" required/>
														<input type="text" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${TotalCountAuditItem}].pic" required/>
														<input onchange="validationTimeAudit(${TotalCountAuditItem})" type="date" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" id = "dealine${TotalCountAuditItem}" name = "audit[${TotalCountAuditItem}].dealine" required/>
														<input type="text" style="flex: 1; height: 100%;" class="bordered-right flex-row-container none-border-top form-control none-border-radius" name = "audit[${TotalCountAuditItem}].note" required/>
													</div></div></div>`);
	TotalCountAuditItem++;
})

function removeAuditUpdate(i) {
	var idAuditItem = document.getElementById('auditId' + i).value;
	if (idAuditItem != 0) {
		if (confirm("Do Want to delete this audit?")) {
			fetch('http://localhost:8080/api/v1/audit/delete/' + idAuditItem, {
			method: 'DELETE',
		})
			.then(res => res.text()) // or res.json()
			.then(res => console.log(res))
		document.getElementById('auditId' + i).value = "0";
		document.getElementById('dateAudit' + i).value = "";
		document.getElementById('relatedPeople' + i).value = "";
		document.getElementById('actionAudit' + i).value = "";
		document.getElementById('pic' + i).value = "";
		document.getElementById('dealine' + i).value = "";
		document.getElementById('noteAudit' + i).value = "";
		}
	}else{
		$('#audit-row-' + i).remove();
	}
}
function validationTimeAudit(i) {
	var startDateAudit = document.getElementById("dateAudit" + i).value;
	var endDateAudit = document.getElementById("dealine" + i).value;
	if ((Date.parse(startDateAudit) >= Date.parse(endDateAudit))) {
		document.getElementById("dealine" + i).value = "";
		alert("End date must be greater than start date!");
	}
}