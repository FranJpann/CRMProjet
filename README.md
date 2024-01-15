# How to run ?

## Run InternalCRM and VirtualCRM using gradlew

VirtualCRM :
```gradle
./gradlew :VirtualCRM:appRun
```

InternalCRM :
```gradle
./gradlew :InternalCRM:run
```

Run both VirtualCRM & InternalCRM :
```gradle
./gradlew --parallel :VirtualCRM:appRun :InternalCRM:run
```

## Run CommandLineTool (Client) using gradlew

### Arguments

Argument availables :
```findLeads lowAnnualRevenue highAnnualrevenue State```
```findLeadsByDate startDate(YYYY-MM-DD) endDate(YYYY-MM-DD)```
```mergeLeads```

### Execution

CommandLineTool :
```gradle
./gradlew :CommandLineTool:run --args="(arguments here)"
```

exemple :
```gradle
./gradlew :CommandLineTool:run --args="findLeads 0 900000000 NC"
```

## Run modules with bash_command-line-tool

### Arguments availables

`appRun` : Run InternalCRM & VirtualCRM

`findLeads lowAnnualRevenue highAnnualrevenue State`
`findLeadsByDate startDate(YYYY-MM-DD) endDate(YYYY-MM-DD)`
`mergeLeads`

### Execution

```sh
./bash_command-line-tool args
```