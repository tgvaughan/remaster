## Scripts useful for plotting and manipulating trajectory data
## using ggplot and friends.

library(dplyr)
library(readr)
library(stringr)
library(tidyr)
library(ggplot2)

parseTrajectory <- function(trajStr) {
  strValues <- str_split(str_split(trajStr, ",")[[1]], ":", simplify = TRUE)

  vars <- str_split(strValues[1,], "=", simplify=TRUE)[-1,1]
  res <- tibble(t=as.numeric(str_split(strValues[,1], "=", simplify=TRUE)[,2]))

  for (idx in 1:length(vars))
      res[vars[idx]] <- as.numeric(str_split(strValues[,idx+1], "=", simplify=TRUE)[,2])

  return(res)
}

loadTrajectories <- function(filenames) {
    df <- NULL

    for (filename in filenames) {
        cat(paste("Loading", filename,"..."))
        df_in <- read_tsv(filename, col_types="ic")

        df <- NULL
        for (row in 1:(dim(df_in)[1])) {
            trajStr <- df_in[row,2]
            trajStates <- parseTrajectory(trajStr) %>% pivot_longer(cols=!t, names_to="variable")
            trajStates$Sample <- df_in$Sample[row]
            df <- bind_rows(df, trajStates)
        }
    }
    
    cat("done.\n")
    
    return(df)
}

plotTrajectories <- function(filenames) {
    ggplot(loadTrajectories(filenames),
           aes(x=t, y=value, col=variable,
               group=interaction(variable,factor(Sample)))) +
        geom_step()
}
