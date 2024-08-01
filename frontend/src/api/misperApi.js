import apiController from "./apiController";

// misper: 실종자
const misperApi = {
    /**
     * @returns {object}
     * occrde: string 실종일자
     * alldressingDscd:
     * ageNow
     * age
     * writngTrgetDscd
     * sexdstnDscd
     * occrAdres
     * nm
     * nltyDscd
     * height
     * bdwgh
     * frmDscd: string
     * faceshpeDscd: string
     * hairshpeDscd: string
     * haircolrDscd: string
     * msspsnIdntfccd: Int 실종자 Id
     * tknphotoFile: btye[] 실종자 사진
     * @param roomId
     */

    getMisperInfos: async (roomId) => {
        return await apiController.get('/missing-persons/find-info', {roomId: roomId});
    }
}

export default misperApi;