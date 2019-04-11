// const APIS = process.env.API_HOST === 'development' ? '' : '/apis';
const basePath = '.';

//获取菜单
export const CHECKUSERS = basePath+'/users/getUserByCountAndPassword';

//提交note内容
export const SUBMIT_CONTENT= basePath+'/notes/addNotes';

//获取笔记
export const GET_NOTES = basePath + '/notes/findNotesByUserId/';

//获取笔记
export const UPDATE_NOTES = basePath + '/notes/updateNote';

//获取jira列表
export const GET_TASKS = basePath + '/tasks/findAllTasks';

//获取jira列表
export const GET_MUSICS = basePath + '/tasks/getMusicByName';

//获取token
export const GET_ACCESS_TOKEN = basePath + '/viewb/getToken';
//根据source类型获取查询内容
export const CONTENT_BY_SOURCECLASS = basePath + '/viewb/listItem'

//联想词
export const ASSOCIATIONWORD = basePath + '/viewb/associationWord'

//新页面
export const SCHEME_DISEASE_GRAPH = basePath + '/knowledge/getDataByDisease';

//新页面新接口
export const SCHEME_DISEASE_GRAPH_NEW = basePath + '/knowledge/getDataByDiseaseNer';
