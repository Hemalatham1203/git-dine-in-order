package com.example.dio.mapper;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.model.Tables;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    /**
     * Converts a TableRequest DTO to a Tables entity.
     * @param tableRequest The request object containing table details.
     * @return A Tables entity mapped from the given TableRequest.
     */
    Tables mapToTableEntity(TableRequest tableRequest);

    /**
     * Converts a Tables entity to a TableResponse DTO.
     * @param tables The entity containing table details.
     * @return A TableResponse DTO mapped from the given Tables entity.
     */
    TableResponse mapToTableResponse(Tables tables);
}
