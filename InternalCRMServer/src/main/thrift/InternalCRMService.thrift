namespace java internalcrm.thrift

struct ModelTO{
    1: string firstname,
    2: string lastname,
    3: double annualRevenue,
    4: string phone,
    5: string street,
    6: string postalCode,
    7: string city,
    8: string country,
    9: string creationDate,
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
        1: string startDate,
        2: string endDate
    ),

    void deleteLead(1: InternalLeadDTO lead),

    void addLead(1: InternalLeadDTO lead)
}