// Written by MongoDB Shell (mongosh)'s standards: https://www.mongodb.com/docs/mongodb-shell/write-scripts/
// require method did not work, so cat is used to read the json files

db = db.getSiblingDB('phyloviz-web-platform')

const workflowTemplates = cat('docker-entrypoint-initdb.d/workflow-templates.json')
const toolTemplates = cat('docker-entrypoint-initdb.d//tool-templates.json')

db['workflow-templates'].insertMany(JSON.parse(workflowTemplates))
db['tool-templates'].insertMany(JSON.parse(toolTemplates))