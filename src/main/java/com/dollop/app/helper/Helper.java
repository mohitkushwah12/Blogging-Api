package com.dollop.app.helper;

import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import com.dollop.app.dtos.PagebleResponse;

public class Helper 
{
	//User -> U and UserDto -> V
	public static <U,V> PagebleResponse<V> getPageableResponse(Page<U> page, Class<V> type) 
	{
		List<U> entity = page.getContent();
		List<V> dtoList = entity.stream().map(object ->
					new ModelMapper().map(object, type))
				.collect(Collectors.toList());
		PagebleResponse<V> response = new PagebleResponse<>();
		response.setContent(dtoList);
		response.setPageNumber(page.getNumber());
		response.setPageSize(page.getSize());
		response.setTotalElements(page.getTotalElements());
		response.setTotalPages(page.getTotalPages());
		response.setLastPage(page.isLast());
		return response;
	}
}
