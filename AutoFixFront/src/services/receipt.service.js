import httpClient from "../http-common";

const getAllReceipts = () => {
    return httpClient.get('/api/v1/receipt/');
}

const createReceipt = (plate) => {
    return httpClient.post(`/api/v1/receipt/${plate}`);
}

const applyCoupon = (plate, coupons) => {
    return httpClient.post(`/api/v1/receipt/coupon/${plate}`, coupons);
}

const applyRealPayment = (plate) => {
    return httpClient.post(`/api/v1/receipt/realpayment/${plate}`);
}

export default { 
    getAllReceipts, 
    createReceipt, 
    applyCoupon, 
    applyRealPayment 
};
