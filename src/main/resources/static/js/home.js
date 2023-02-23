function showTransactions(accountID) {
    $('#transactions' + accountID).toggle();
}

$('#searchBtn').on('click', function () {

    $('#customerDetails').replaceWith("<div id=\"customerDetails\" class=\"col-4 mt-3\"></div>");

    $('#accountDiv').replaceWith("<div id=\"accountDiv\" class=\"col-4\">\n" +
        "                <h3 id=\"placeholder\"></h3>\n" +
        "            </div>");

    $.ajax({
        type: "GET",
        url: "customers/" + $('#customerId').val(),
        contentType: "application/json",
        data: {},
        success: function (result) {

            $('#customerDetails').replaceWith("<div id=\"customerDetails\" class=\"col-4 mt-3\">" +
                "<h6>" +
                "Customer Name: " + result.lastName + " " + result.firstName +
                "</h6>" +
                "<br>" +
                "<h6>*Click on the account to see transactions</h6>" +
                "</div>" +
                "<br>");

            for (var i = 0; i < result.accountList.length; i++) {
                var accountId = "account" + i;
                var accountName = "Account" + (i + 1);
                var accountButton = "accountBtn" + i;
                var transactionsName = "transactions" + i;

                if (i !== 0) {
                    $("<div id=\"" + accountId + "\" class=\"mt-3\">" +
                        "<button id=\"" + accountButton + "\" class=\"btn btn-outline-dark w-100\" onClick=\"showTransactions(" + i + ")\">" +
                        "[" + accountName + "] " +
                        "Balance: " + result.accountList[i].balance.toFixed(2) + "€ | " +
                        "Account Type: " + result.accountList[i].accountType + " " +
                        "</button><div id=\"" + transactionsName + "\"></div>" +
                        "</div>" +
                        "<br>").insertAfter($('#account' + (i - 1)));
                    $('#' + transactionsName).hide();
                } else {
                    $('#placeholder').replaceWith("<div id=\"" + accountId + "\" class=\"mt-3\">" +
                        "<button id=\"" + accountButton + "\" class=\"btn btn-outline-dark w-100\" onClick=\"showTransactions(" + i + ")\">" +
                        "[" + accountName + "] " +
                        "Balance: " + result.accountList[i].balance.toFixed(2) + "€ | " +
                        "Account Type: " + result.accountList[i].accountType + " " +
                        "</button><div id=\"" + transactionsName + "\"></div>" +
                        "</div>" +
                        "<br>");
                    $('#' + transactionsName).hide();
                }
                for (var j = 0; j < result.accountList[i].transactionList.length; j++) {

                    var transactionId = "acc" + i + "trans" + j;
                    var transactionName = "Transaction" + (j + 1);

                    if (j !== 0) {
                        $("<div id=\"" + transactionId + "\" class=\"ms-4\">" +
                            "[" + transactionName + "] " +
                            "Amount: " + result.accountList[i].transactionList[j].amount.toFixed(2) + "€" +
                            "</div>"
                        ).insertAfter($('#' + 'acc' + i + 'trans' + (j - 1)));
                    } else {
                        $('#' + transactionsName).append("<div id=\"" + transactionId + "\" class=\"ms-4\">" +
                            "[" + transactionName + "] " +
                            "Amount: " + result.accountList[i].transactionList[j].amount.toFixed(2) + "€" +
                            "</div>")
                    }
                }
            }

        },
        error: function (xhr) {
            if (typeof xhr.responseJSON.message !== 'undefined') {
                alert(xhr.responseJSON.message);
            } else {
                alert(xhr.responseJSON.detail);
            }
        }
    });
})

$('#createCustomerBtn').on('click', function () {
    console.log("step1");
    $.ajax({
        type: "POST",
        url: "customers",
        contentType: "application/json",
        data: JSON.stringify({
            lastName: $('#customerLastName').val(),
            firstName: $('#customerFirstName').val(),
            socialSecurityNumber: $('#customerSSN').val(),
        }),
        success: function (result) {
            alert("Customer created successfully!");
        },
        error: function (xhr) {
            if (typeof xhr.responseJSON.message !== 'undefined') {
                alert(xhr.responseJSON.message);
            } else {
                alert(xhr.responseJSON.detail);
            }
        }
    });
})

$('#createAccountBtn').on('click', function () {
    $.ajax({
        type: "POST",
        url: "accounts",
        contentType: "application/json",
        data: JSON.stringify({
            customerID: $('#customerId2').val(),
            initialCredit: $('#initialCredit').val(),
            accountType: $('#accountType').val(),
        }),
        success: function (result) {
            alert("Account created successfully!");
        },
        error: function (xhr) {
            if (typeof xhr.responseJSON.message !== 'undefined') {
                alert(xhr.responseJSON.message);
            } else {
                alert(xhr.responseJSON.detail);
            }
        }
    });
})