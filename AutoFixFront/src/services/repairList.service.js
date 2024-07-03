import httpClient from "../http-common";


const getAllRepairlists = () => {
    return httpClient.get('/ms/listrepair/');
}

const createRepairlist = (repairList) =>  {
    return httpClient.post(`/ms/listrepair/`, repairList);
}

const deleteRepairlist = (id) => {
    return httpClient.delete(`/ms/listrepair/${id}`);
}

export default { 
    getAllRepairlists,
    createRepairlist,
    deleteRepairlist
};