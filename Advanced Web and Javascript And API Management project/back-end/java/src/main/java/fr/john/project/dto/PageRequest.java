package fr.john.project.dto;

import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
public class PageRequest
  implements Pageable
{
  private final int pageNumber;
  private final int offset;
  
  @Override
  public int getPageNumber()
  {
    return pageNumber;
  }
  
  @Override
  public int getPageSize()
  {
    return 10;
  }
  
  @Override
  public long getOffset()
  {
    return offset;
  }
  
  @Override
  @NotNull
  public Sort getSort()
  {
    return Sort.unsorted();
  }
  
  @Override
  @NotNull
  public Pageable next()
  {
    return new PageRequest(pageNumber + 1, offset + getPageSize());
  }
  
  @Override
  @NotNull
  public Pageable previousOrFirst()
  {
    if (pageNumber <= 1)
    {
      return first();
    }
    return new PageRequest(pageNumber - 1, offset - getPageSize());
  }
  
  @Override
  @NotNull
  public Pageable first()
  {
    return new PageRequest(1, 0);
  }
  
  @Override
  @NotNull
  public Pageable withPage(int pageNumber)
  {
    if (pageNumber <= 1)
    {
      return first();
    }
    return new PageRequest(pageNumber, (pageNumber - 1) * getPageSize());
  }
  
  @Override
  public boolean hasPrevious()
  {
    return pageNumber >= 2;
  }
}
