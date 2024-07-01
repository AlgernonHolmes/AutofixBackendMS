import httpClient from "../http-common";

const getAllRepairDetails = () => {
    return httpClient.get('/ms/repair/repairdetail/');
}

const createRepairDetail = (repairDetail) => {
    return httpClient.post(`/ms/repair/repairdetail/`, repairDetail);
}

const updateRepairDetail = (id, updatedRepairDetail) => {
    return httpClient.put(`/ms/repair/repairdetail/${id}`, updatedRepairDetail);
}

const deleteRepairDetailById = (id) => {
    return httpClient.delete(`/ms/repair/repairdetail/${id}`);
}

export default { 
    getAllRepairDetails, 
    createRepairDetail, 
    updateRepairDetail, 
    deleteRepairDetailById
};

