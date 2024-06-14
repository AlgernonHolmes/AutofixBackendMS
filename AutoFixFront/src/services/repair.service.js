import httpClient from "../http-common";

const getAllRepairs = () => {
    return httpClient.get('/api/v1/repair/');
}

const getRepairsByPlate = (plate) => {
    return httpClient.get(`/api/v1/repair/${plate}`);
}

const createRepair = (repair) => {
    return httpClient.post(`/api/v1/repair/`, repair);
}

const updateRepair = (updatedRepair) => {
    return httpClient.put("/api/v1/repair/", updatedRepair);
}

const deleteRepairById = (id) => {
    return httpClient.delete(`/api/v1/repair/${id}`);
}

const getR2 = () => {
    return httpClient.get(`/api/v1/repair/r2`)
}

const getR3 = () => {
    return httpClient.get(`/api/v1/repair/r3`)
}


const getR4 = () => {
    return httpClient.get(`/api/v1/repair/r4`)
}

export default { 
    getAllRepairs, 
    getRepairsByPlate, 
    createRepair, 
    updateRepair, 
    deleteRepairById,
    getR2,
    getR3,
    getR4
};
