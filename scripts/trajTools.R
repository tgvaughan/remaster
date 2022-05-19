## Scripts useful for plotting and manipulating trajectory data
## using ggplot and friends.

library(tidyverse)

parseTrajectory <- function(trajStr) {
  strValues <- str_split(str_split(trajStr, ";")[[1]], ":", simplify = TRUE)
  vars <- str_split(strValues[1,], "=", simplify=TRUE)[,1]
  colnames(strValues) <- vars


  res <- as_tibble(strValues) %>%
      mutate(across(everything(),
                    function(x) {str_split(x,"=",simplify=TRUE)[,2]})) #%>%

  for (var in vars[-1]) {
      n <- length(str_split(res[1,var],",",simplify=TRUE))
      res <- res %>% separate(var, into=paste0(var,"__",0:(n-1)), sep=",")
  }

  res <- res %>%
      pivot_longer(!t) %>%
      separate(name,into=c("population","index"), sep="__") %>%
      mutate(across(!population, as.numeric))

  return(res)
}

loadTrajectories <- function(filenames) {
    df <- NULL

    for (filename in filenames) {
        df_in <- read_tsv(filename, col_types="ic")

        df <- NULL
        for (row in 1:(dim(df_in)[1])) {
            trajStr <- df_in[row,2]
            trajStates <- parseTrajectory(trajStr)
            trajStates$Sample <- df_in$Sample[row]
            df <- bind_rows(df, trajStates)
        }
    }
    
    return(df)
}

plotTrajectories <- function(filenames) {
    ggplot(loadTrajectories(filenames),
           aes(x=t, y=value, col=interaction(population, factor(index)),
               group=interaction(population,factor(index), factor(Sample)))) +
        geom_step()
}
