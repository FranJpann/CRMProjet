namespace java internalcrm.services.thrift.impl

struct calendar {
    1: i32 day,
    2: i32 month,
    3: i32 year
}

struct ModelTO{
    1: string firstname,
    2: string lastname,
    3: i64 annualRevenue,
    4: string phone,
    5: string street,
    6: string postalCode,
    7: string city,
    8: string country,
    9: calendar creationDate,
    10: string company,
    11: string stat
}

struct InternalLeadDTO {
}

service InternalCRMService {

    list<InternalLeadDTO> findLeads(
        1: i64 lowAnnualRevenue,
        2: i64 highAnnualRevenue,
        3: string state
    ),

    list<InternalLeadDTO> findLeadsByDate(
        1: calendar startDate,
        2: calendar endDate
    ),

    void deleteLead(1: InternalLeadDTO lead),

    void addLead(1: InternalLeadDTO lead)
}