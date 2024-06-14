import httpClient from "../http-common";

const getAllRepairDetails = () => {
    return httpClient.get('/ms/repairdetail');
}

const createRepairDetail = (repairDetail) => {
    return httpClient.post(`/ms/repairdetail`, repairDetail);
}

const updateRepairDetail = (id, updatedRepairDetail) => {
    return httpClient.put(`/ms/repairdetail/${id}`, updatedRepairDetail);
}

const deleteRepairDetailById = (id) => {
    return httpClient.delete(`/ms/repairdetail/${id}`);
}

export default { 
    getAllRepairDetails, 
    createRepairDetail, 
    updateRepairDetail, 
    deleteRepairDetailById
};

