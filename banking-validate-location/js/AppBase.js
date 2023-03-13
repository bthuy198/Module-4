class AppBase {
    static DOMAIN_SERVER = 'http://localhost:3300';

    static API_SERVER = this.DOMAIN_SERVER + '';

    static API_CUSTOMER = this.API_SERVER + '/customers';
    static API_ALL_CUSTOMERS = this.API_SERVER + '/customers?deleted=0';

    static API_DEPOSIT = this.API_SERVER + '/deposits';
    static API_WITHDRAW = this.API_SERVER + '/withdraws';
    static API_TRANSFER = this.API_SERVER + '/transfers';
}