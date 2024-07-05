import httpClient from "../http-common";

/* REPAIR DETAIL SECTION */

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

/* REPAIR SECTION */

const createRepair = (repair) => {
    return httpClient.post(`/ms/repair/`, repair);
}

const getAllRepairs = () => {
    return httpClient.get('/ms/repair/');
}

const deleteRepairById = (id) => {
    return httpClient.delete(`/ms/repair/${id}`);
}

const updateRepair = (id, updatedRepair) => {
    return httpClient.put(`/ms/repair/${id}`, updatedRepair);
}

export default { 
    getAllRepairDetails, 
    createRepairDetail, 
    updateRepairDetail, 
    deleteRepairDetailById,
    createRepair,
    getAllRepairs,
    updateRepair,
    deleteRepairById
};

