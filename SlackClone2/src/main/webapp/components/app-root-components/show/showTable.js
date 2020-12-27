function buildUserTable() {
    buildTableFromJsonApi("user/get", "#show-table")
}

function buildCompanyTable() {
    buildTableFromJsonApi("company/get", "#show-table")
}

function buildMessageTable() {
    buildTableFromJsonApi("message/get", "#show-table")
}

function buildChannelTable() {
    buildTableFromJsonApi("channel/get", "#show-table")
}

function buildInvitationTable() {
    buildTableFromJsonApi("invitation/get", "#show-table")
}